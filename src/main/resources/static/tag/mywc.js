var cv = document.getElementById("canvas");
var cxt = cv.getContext('2d');
//cxt.save();
//var back_font = "px Arial";
var list2=[["Layout",346],["Xiong",4015],["Kai",11195],["Cloud",153],["Word",148], ["China", 246],["Students", 286],["August", 85],["Hello",112],["key",88],["EdWordle",500],["Rigid Body",192],["method",102]];
var list1=[["Layout",846],["group",534],["cats",336],["Hope",996],["word",453],["cloud",412],["Sunday",733]];
var list3=[["Layout",846],["hello",643]];
/*var mylist = [${wordString}];
var wlist=[mylist];*/
var list=[];
var len,twoBoxLen,max,min; //max:最高词频 min:最低词频

var cw = cv.width;
var ch = cv.height;
//alert(cw+";;;"+ch)
var maxFontSize = 60;
var minFontSize = 10;
var size = 0;
var color;
var rotateDegree;

var switch_box=true; //外边框开关
var switch_Letter=true; //字母边框开关
var switch_Word=true; //单词边框开关
var wordlist = []; //保存所有单词的外边界及word、size、color等信息
var wlTwoBox = [];//wordlistTwoBox:相对大小高于最大单词大小*0.5的所有单词的字母边界（两级盒子模型）
var imgData; //记录每次全局布局

var inCircle=[]; //保存在圈内的单词的序号
var outCircle=[]; //保存在圈外的单词的序号
var radius; //半径
var centerW = []; //the center point of a word

var editVI=-1; //editVersionIndex
var editVersion=[]; //编辑版本

var movex=0;  //鼠标移动的水平大小
var movey=0;  //鼠标移动的垂直大小
var selectWords=[];  //鼠标选择的单词（保存的是单词的下标）

var cwl=0;
function changeWL(){
	list.length=0;
	for(var i in wlist[cwl]){
		list.push(wlist[cwl][i]);
	}
	cwl=(cwl+1)%wlist.length;
	min = list[0][1]; //最低词频
	max = 0;          //最高词频
	len = list.length;
	twoBoxLen = Math.floor(len/2);
	//var twoBoxLen=len
	for (var i = 0; i < len; i++) {
	    if (min > list[i][1]) {
	        min = list[i][1];
	    }
	    if (max < list[i][1]) {
	        max = list[i][1];
	    }
	}
	changeLayout();
}

function changeWLBP(wl){ //change wordlist by person
    list.length=0;
    for(var i in wl){
        list.push(wl[i]);
    }
    //cwl=(cwl+1)%wlist.length;
    min = list[0][1]; //最低词频
    max = 0;          //最高词频
    len = list.length;
    twoBoxLen = Math.floor(len/2);
    //var twoBoxLen=len
    for (var i = 0; i < len; i++) {
        if (min > list[i][1]) {
            min = list[i][1];
        }
        if (max < list[i][1]) {
            max = list[i][1];
        }
    }
    changeLayout();
}

function showWLinModal(){
    var str="";
    for(var i=0;i<list.length;i++){
        str+=list[i][0]+" "+list[i][1];
        str+="\n";
    }
   // alert(str);
    document.getElementById("modal_body").innerHTML = str;
}

function clearWL() {
    document.getElementById("modal_body").innerHTML="";
    //alert(document.getElementById("modal_body").innerText)
}
//更新词汇
function inputWL(){
    var rowdata = $('#modal_body').val();
    //console.log(rowdata);
    var wl=[];
    //var rowdata = "Xiong 32\nKai 44";
    var str = /[a-z]/i;
    var num = /[0-9]/;
    for(var i=0;i<rowdata.length;i++){
        var word="";
        var frequency="";
        var freq = 0;
        while(str.test(rowdata[i])){
            word+=rowdata[i];
            i++;
        }
        while(/\W/.test(rowdata[i])){
            i++;
        }
        while(num.test(rowdata[i])){
            frequency+=rowdata[i];
            i++;
        }
        var freq = parseInt(frequency);
        console.log("word="+word);
        console.log("freq="+freq);
        console.log("isNaN(freq)="+isNaN(freq));

        if(word==""||freq<=0||isNaN(freq)){
        	alert("Invalid Input!");
            return 0;
        }else{
            //wl[word]=freq;
            wl.push([word,freq]);
        }
    }
    if(wl.length<=0){
        alert("词汇表不能为空!");
    }else{
        changeWLBP(wl);
    }
}

cv.onmousedown = function(e){
	var ev = window.event || e;
	var mx = ev.layerX || ev.offsetX;
	var my = ev.layerY || ev.offsetY;
	document.getElementById("coordinate").innerHTML=mx+":"+my;
	
	if(selectWords.length){
		for(var i in selectWords){
			var x1=wordlist[selectWords[i]].border[0];
			var y1=wordlist[selectWords[i]].border[1];
			var x2=x1+wordlist[selectWords[i]].border[2];
			var y2=y1+wordlist[selectWords[i]].border[3];
			if(mx>=x1&&mx<=x2&&my>=y1&&my<=y2){ //词组的移动
				
				var sNWB=[]; //select normal word-level boxes
				var sTLB=[]; //select two-level boxes
				cxt.putImageData(imgData,0,0);//首先将所有单词的边框清空
				for(var j in selectWords){
					if(selectWords[j]<twoBoxLen){
						var wordl=wlTwoBox[selectWords[j]].box;
						//var sLetter=[]; //selectLetter
						for(var k in wordl){
							var xl=wordl[k].border[0]; //x_left
	  						var yt=wordl[k].border[1]; //x_top
	  						cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
	  						
	  						sTLB.push({
	  							i:selectWords[j],
	  							//xl:xl,
	  							//yt:yt,
	  							dx:mx-xl,
	  							dy:my-yt,
	  							img:wordl[k].border[4]
	  						});
						}
					}else{
						var xl=wordlist[selectWords[j]].border[0];
						var yt=wordlist[selectWords[j]].border[1];
						var wordw=wordlist[selectWords[j]].border[2];
						var wordh=wordlist[selectWords[j]].border[3];
						
						sNWB.push({
							i:selectWords[j],
							dx:mx-xl,
							dy:my-yt,
							//imageData:cxt.getImageData(xl,yt,wordw,wordh),
							img:wordlist[selectWords[j]].border[4]
						})
						cxt.clearRect(xl,yt,wordw,wordh);
					}
					
				}
				
				var imageDataOtherWords = cxt.getImageData(0,0,cw,ch); //清空所选单词，以保存其他单词的信息
				//恢复单词元素信息
				for(var j=0,l=sNWB.length;j<l;j++){
					cxt.putImageData(sNWB[j].img,mx-sNWB[j].dx,my-sNWB[j].dy);
				}
				for(var j=0,l=sTLB.length;j<l;j++){
					cxt.putImageData(sTLB[j].img,mx-sTLB[j].dx,my-sTLB[j].dy);
				}
				//恢复单词边框信息
				for(var i in selectWords){
					showSelectWords(selectWords[i]);
				}
				
				var mnx,mny;
				cv.onmousemove = function(e){
					cxt.putImageData(imageDataOtherWords,0,0);
	  				var ev = window.event || e;
	  				mnx = ev.layerX || ev.offsetX;
	  				mny = ev.layerY || ev.offsetY;
	  				document.getElementById("coordinate").innerHTML=mnx+":"+mny;
	  				for(var j=0,l=sNWB.length;j<l;j++){
	  					cxt.putImageData(sNWB[j].img,mnx-sNWB[j].dx,mny-sNWB[j].dy);
	  				}
	  				for(var j=0,l=sTLB.length;j<l;j++){
						cxt.putImageData(sTLB[j].img,mnx-sTLB[j].dx,mny-sTLB[j].dy);
					}
					movex=mnx-mx;
					movey=mny-my;
					for(var i in selectWords){
						cxt.strokeStyle="red";
		  				cxt.strokeRect(wordlist[selectWords[i]].border[0]+movex,wordlist[selectWords[i]].border[1]+movey,wordlist[selectWords[i]].border[2],wordlist[selectWords[i]].border[3]);
					}
	  			}
				cv.onmouseup = function(e){
					cv.onmousemove = null;
					//fresh();
					if(movex||movey){ //表明移动了鼠标
						cxt.putImageData(imageDataOtherWords,0,0);
						for(var j in sNWB){
							wordlist[sNWB[j].i].border[0]+=movex;
							wordlist[sNWB[j].i].border[1]+=movey;
							cxt.putImageData(sNWB[j].img,mnx-sNWB[j].dx,mny-sNWB[j].dy);
						}
						var k=-1;
						for(var j in sTLB){
							if(k!=sTLB[j].i){
								k=sTLB[j].i;
								wordlist[k].border[0]+=movex;
								wordlist[k].border[1]+=movey;
								wlTwoBox[k].minX+=movex;
								wlTwoBox[k].minY+=movey;
								for(var lb in wlTwoBox[k].box){ //letterborder
									wlTwoBox[k].box[lb].border[0]+=movex;
									wlTwoBox[k].box[lb].border[1]+=movey;
								}
							}
							cxt.putImageData(sTLB[j].img,mnx-sTLB[j].dx,mny-sTLB[j].dy);
						}
						fresh();
						//imgData=cxt.getImageData(0,0,cw,ch);
						editSave();
						//compact();
						for(var j in selectWords){
							showSelectWords(selectWords[j]);
						}
						//getTowLevelBorder();
						movex=0;
						movey=0;
					}
				}
				return;
			}//-if
			//下面是选中了一组单词，却不进行移动的情况
		}//-for
	}
	
	selectWords.length=0; //说明没有选中词组
	var i=0;
	outerloop:
	for(; i<len;i++){
		//alert(i+":"+wordlen);
		if(i<twoBoxLen){ //大尺寸单词
			var wordlen = wordlist[i].word.length;
			//alert(i+":"+wordlen)
			if(wordlist[i].word.search(' ')>=0){ //不存在为-1
				wordlen--;
			}
			for(var j=0;j<wordlen;j++){
				//alert(i+": "+j)
				var letter=wlTwoBox[i].box[j];
				var x1=letter.border[0];
				var x2=x1+letter.border[2];//x2=x1+wordw;
				var y1=letter.border[1];
				var y2=y1+letter.border[3];//y2=y1+wordh;
				if(mx>=x1&&mx<=x2&&my>=y1&&my<=y2){
					//document.getElementById("word").innerHTML=list[i][0]+" : "+list[i][1];
					document.getElementById("word").innerHTML=wordlist[i].word+" : "+wordlist[i].freq;
					document.getElementById("font").style.display="inline"; //inline内联元素，不会换行；block块级标签，换行
					document.getElementById("color").value=wordlist[i].color;
					//showWord(i);
					document.getElementById("color").onchange = function(){
			  			//alert(this.value);
			  			changeWordColor(this.value,i);
			  		}
					document.getElementById("font_type").value=wordlist[i].font;
					document.getElementById("font_type").onchange = function(){
			  			//alert(this.value);
						changeWordFont(this.value,i);
			  		}
					
					cxt.putImageData(imgData,0,0); //先清空一下
					var wordl=wlTwoBox[i].box;
					var sLetter=[]; //selectLetter
					for(var k in wordl){
						var xl=wordl[k].border[0]; //x_left
  						var yt=wordl[k].border[1]; //x_top
  						cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
  						
  						sLetter.push({
  							//xl:xl,
  							//yt:yt,
  							dx:mx-xl,
  							dy:my-yt,
  							img:wordl[k].border[4]
  						});
					}
					
					//alert(wordlistTwoBox[i].word);
					/*
					var x1=wordlist[i].border[0]-1; //x_left
  					var y1=wordlist[i].border[1]-1; //x_top
  					var dx=mx-x1;
  					var dy=my-y1;
  					var wordw=wordlist[i].border[2]+2;
  					var wordh=wordlist[i].border[3]+2;
  					var selectWord = cxt.getImageData(x1,y1,wordw,wordh);
  					cxt.clearRect(x1,y1,wordw,wordh);
  					*/
					var imageDataOtherWords = cxt.getImageData(0,0,cw,ch);
					/*
					for(var k in sLetter){
						cxt.putImageData(sLetter[k].img,sLetter[k].xl,sLetter[k].yt);
					}
					*/
					showWord(i);
					var wbx=wordlist[i].border[0];
					var wby=wordlist[i].border[1];
					var wbw=wordlist[i].border[2];
					var wbh=wordlist[i].border[3];
					
					var mnx,mny;
					cv.onmousemove = function(e){
						//cxt.clearRect(mnx-dx,mny-dy,wordw,wordh);
						cxt.putImageData(imageDataOtherWords,0,0);
		  				var ev = window.event || e;
		  				mnx = ev.layerX || ev.offsetX;
		  				mny = ev.layerY || ev.offsetY;
		  				document.getElementById("coordinate").innerHTML=mnx+":"+mny;
		  				//cxt.globalCompositeOperation="lighter";//重叠图形颜色叠加
		  				for(var k in sLetter){
							cxt.putImageData(sLetter[k].img,mnx-sLetter[k].dx,mny-sLetter[k].dy);
						}
		  				//alert(mnx)
						//showWord(i);
		  				//showSelectWords(i);
  						movex=mnx-mx;
  						movey=mny-my;

		  				cxt.strokeStyle="red";
		  				cxt.strokeRect(wbx+movex,wby+movey,wbw,wbh);
		  			}
		  			
					//cxt.putImageData(imageData,mnx,mny);
					//alert(editVersion[0].wordlist[0].word+"\n"+editVersion[0].wordlist[0].border);
					cv.onmouseup = function(e){
						//alert(movex+":"+movey);
						//console.log("1");
						cv.onmousemove = null;
						//alert(editVersion[0][0][0].word+"\n"+editVersion[0][0][0].border);
						//fresh();
						if(movex||movey){
							cxt.putImageData(imageDataOtherWords,0,0);
							wordlist[i].border[0]+=movex;
							wordlist[i].border[1]+=movey;
							/*
							cxt.clearRect(mnx-dx,mny-dy,wordw,wordh);
							cxt.putImageData(wordlist[i].border[4],mnx-dx+1,mny-dy+1);
							*/
							//alert(movex+":"+movey);
							//imgData=getImageData(0,0,cw,ch);
							wlTwoBox[i].minX+=movex;
							wlTwoBox[i].minY+=movey;
							for(var lb in wlTwoBox[i].box){ //letterborder
								wlTwoBox[i].box[lb].border[0]+=movex;
								wlTwoBox[i].box[lb].border[1]+=movey;
								cxt.putImageData(wlTwoBox[i].box[lb].border[4],wlTwoBox[i].box[lb].border[0],wlTwoBox[i].box[lb].border[1]);
							}
							//alert(editVersion[0][0][0].word+"\n"+editVersion[0][0][0].border);
							//alert(editVersion[0].wordlist[0].word+"\n"+editVersion[0].wordlist[0].border);
							fresh();
							//imgData=cxt.getImageData(0,0,cw,ch);
							
							editSave();
							//compact();
							//showWord(i);
							//getTowLevelBorder();
							cxt.strokeStyle="red";
			  				cxt.strokeRect(wbx+movex,wby+movey,wbw,wbh);
							movex=0;
							movey=0;
						}
						
					}
					//cv.onmouseup = function(e){cv.onmousemove = null;}
					break outerloop;
				}//-if
			}//-for
		}else{ //小尺寸单词
			
			var x1=wordlist[i].border[0];
			var y1=wordlist[i].border[1];
			var x2=x1+wordlist[i].border[2];
			var y2=y1+wordlist[i].border[3];
			if(mx>=x1&&mx<=x2&&my>=y1&&my<=y2){
				//alert(i);
				//document.getElementById("word").innerHTML=list[i][0]+" : "+list[i][1];
                document.getElementById("word").innerHTML=wordlist[i].word+" : "+wordlist[i].freq;
                document.getElementById("font").style.display="inline"; //inline内联元素，不会换行；block块级标签，换行
				document.getElementById("color").value=wordlist[i].color;
				showWord(i);
				document.getElementById("color").onchange = function(){
					changeWordColor(this.value,i);
		  		}
				document.getElementById("font_type").value=wordlist[i].font;
				document.getElementById("font_type").onchange = function(){
		  			//alert(this.value);
					changeWordFont(this.value,i);
		  		}
				
				x1--; //x_left
				y1--; //y_top
				var dx=mx-x1;
				var dy=my-y1;
				var wordw=wordlist[i].border[2]+2;
				var wordh=wordlist[i].border[3]+2;
				var selectWord = cxt.getImageData(x1,y1,wordw,wordh);
				cxt.clearRect(x1,y1,wordw,wordh);
					
				var imageDataOtherWords = cxt.getImageData(0,0,cw,ch);
				cxt.putImageData(selectWord,x1,y1);
				//showWord(i);
				var mnx,mny;
				cv.onmousemove = function(e){
					//cxt.clearRect(mnx-dx,mny-dy,wordw,wordh);
					cxt.putImageData(imageDataOtherWords,0,0);
	  				var ev = window.event || e;
	  				mnx = ev.layerX || ev.offsetX;
	  				mny = ev.layerY || ev.offsetY;
	  				document.getElementById("coordinate").innerHTML=mnx+":"+mny;
	  				//cxt.globalCompositeOperation="lighter";//重叠图形颜色叠加
	  				cxt.putImageData(selectWord,mnx-dx,mny-dy);

					movex=mnx-mx;
					movey=mny-my;
	  				
	  			}
				
				cv.onmouseup = function(e){
					cv.onmousemove = null;
					//fresh();
					if(movex||movey){
						wordlist[i].border[0]+=movex;
						wordlist[i].border[1]+=movey;
						cxt.clearRect(mnx-dx,mny-dy,wordw,wordh);
						cxt.putImageData(wordlist[i].border[4],mnx-dx+1,mny-dy+1);
						fresh();
						//imgData=cxt.getImageData(0,0,cw,ch);
						editSave();
						//compact();
						showWord(i);
						//getTowLevelBorder();
						movex=0;
						movey=0;
					}
				}
				break outerloop;
			}
		}//-else
	}
	
	if(i>=len){ //表示鼠标不在单词上
		cxt.putImageData(imgData,0,0);
		document.getElementById("word").innerHTML="";
		document.getElementById("font").style.display="none";
		
		var mnx;
		var mny;
		//selectWords.length=0;
		cv.onmousemove = function(e){
			cxt.putImageData(imgData,0,0);
			//cxt.clearRect(mx-1,my-1,mnx-mx+2,mny-my+2);
			var ev = window.event || e;
			mnx = ev.layerX || ev.offsetX;
			mny = ev.layerY || ev.offsetY;
			document.getElementById("coordinate").innerHTML=mnx+":"+mny;
			/*
			cxt.strokeStyle="green";
			cxt.strokeRect(mx,my,mnx-mx,mny-my);
			*/
			cxt.fillStyle="rgba(0,0,255,0.1)";
			cxt.fillRect(mx,my,mnx-mx,mny-my);
			for(var i=0;i<len;i++){ //选中词组
				var x1=wordlist[i].border[0];
				var y1=wordlist[i].border[1];
				var x2=x1+wordlist[i].border[2];
				var y2=y1+wordlist[i].border[3];
				if((mx<=x1&&my<=y1&&x2<=mnx&&y2<=mny)||(mnx<=x1&&mny<=y1&&x2<=mx&&y2<=my)){ //两种选择方式：1.向右下角 2.向左上角
					showSelectWords(i);
					if(selectWords.indexOf(i)==-1){//不能用in，in是用来判断属性的
						//alert(mnx+":"+mny);
  						selectWords.push(i);
					}
				}
			}//-for
			
		}//-cv.onmousemove
		cv.onmouseup = function(e){
			//console.log("3")
			cv.onmousemove = null;
			cxt.putImageData(imgData,0,0);
			if(selectWords.length){
				for(var i in selectWords){
					showSelectWords(selectWords[i]);
				}
			}
		}
	}//-if		
	
/*	var wlength = wordlist.length;
	for(var i=0;i<wlength;i++){
		console.log(wordlist[i].word)
	}*/
	
	saveInfo();
}//-cv.onmousedown
/*
cv.onmouseup = function(e){
	//alert(movex+":"+movey);
	cv.onmousemove = null;
}
*/

function saveInfo(){
	var wlength = wordlist.length;
	for(var i=0;i<wlength;i++){
		console.log(wordlist[i].word)
	}
}

function originalWordle(){
	var lx,rx,ty,by;
	lx=rx=ty=by=0;
	lx=100;ty=90;
	cxt.clearRect(0,0,cw,ch);
	wordlist.length=0;
	switch_box=true;
	switch_Letter=true; 
	switch_Word=true;
	cxt.textBaseline = 'top';
	cxt.textAlign='start';
	shuffle(list);
	for(var i=0;i<len;i++){
		
		size = Math.floor((maxFontSize - minFontSize)/(max-min) * (list[i][1]-min/2) +minFontSize);
		size>1.6*maxFontSize?size=1.6*maxFontSize:'';
		cxt.font = size.toString()+"px Arial";
		//alert(size);
		color = randomColor();
		cxt.fillStyle = color;
		
		cxt.save();
		var random=Math.floor(Math.random()*4);
		var x,y;
		switch(random){
		case 0:  //上
			//x=Math.floor(Math.random()*cw);
			x=lx+Math.floor(Math.random()*(cw-lx-rx));
			y=ty;
			break;
		case 1:  //右
			x=cw-rx;
			//y=Math.floor(Math.random()*ch);
			y=ty+Math.floor(Math.random()*(ch-ty-by));
			break;
		case 2:  //下
			x=lx+Math.floor(Math.random()*(cw-lx-rx));
			y=ch-by;
			break;
		case 3:  //左
			x=lx;
			y=ty+Math.floor(Math.random()*(ch-ty-by));
			break;
		}
		//var x=400;
		//var y=200;
		var wordw = Math.floor(cxt.measureText(list[i][0]).width);
		var wordh = Math.floor(cxt.measureText("Pk").width);
		var rotateRate = size/(maxFontSize+minFontSize);
		rotateRate = rotateRate>0.75?(Math.pow(rotateRate,1.6)>0.9?0.9:Math.pow(rotateRate,1.6)):(0.5+rotateRate/10+Math.pow(rotateRate,5));
		//alert(rotateRate);
		var rotateDegree=Math.random()>rotateRate?(Math.random()>0.5?1:-1):0;
		//if(i<twoBoxLen) rotateDegree=0;
		//var rotateDegree=0;
		//alert(rotateDegree);
		//alert(x+":"+y+":"+wordw+":"+wordh); 
		
		if(rotateDegree){
			if(rotateDegree==1){
				(x-wordh<0)?x=wordh:'';
				(y+wordw>ch)?y=ch-wordw:'';
				//alert(x+":"+y+":"+wordw+":"+wordh); 
			}else{
				(x+wordh>cw)?x=cw-wordh:'';
				(y-wordw<0)?y=wordw:'';
				//alert(x+":"+y+":"+wordw+":"+wordh); 
			}
		}else{
			(x+wordw>cw)?x=cw-wordw:'';
			(y+wordh>ch)?y=ch-wordh:'';
			//alert(x+":"+y+":"+wordw+":"+wordh); 
		}
		
		//cxt.fillText(list[i][0],x,y);
		//cxt.fillRect(x,y,wordw,wordh);
		cxt.translate(x,y);
		
		cxt.rotate(rotateDegree*Math.PI/2);
		cxt.fillText(list[i][0],0,0);
		//cxt.fillRect(0,0,wordw,wordh);
		cxt.restore();
		
		//cxt.strokeRect(x-wordh,y,wordh,wordw);
		//cxt.strokeRect(x,y,wordw,wordh);
		//rotateDegree=0;
		
		//alert(x+":"+y+":"+wordw+":"+wordh); 
		if(rotateDegree){
			//alert(rotateDegree+" x:"+x+" y:"+y+":"+wordw+":"+wordh);
			var change;
			if(rotateDegree==1){
				x=x-wordh;
				change = wordh;
				wordh=wordw;
				wordw=change;
				//alert(x+":"+y+":"+wordw+":"+wordh); 
			}else{
				y=y-wordw;
				change = wordh;
				wordh=wordw;
				wordw=change;
			}
		}
		
		var border = getBorder(x,y,wordw,wordh);
		//wordlist.push([list[i][0],size,color,border,rotateDegree]);
		wordlist.push({
			word:list[i][0],
			size:size,
			font:"Arial",
			color:color,
			border:border,
			rotateDegree:rotateDegree
		});
		//cxt.strokeStyle="red";
		//cxt.strokeRect(x,y,wordw,wordh);
		
		//alert(wordlist.length)
		//alert(wordlist[i][0]+":"+wordlist[i][1]+":"+wordlist[i][2]+":"+wordlist[i][3]+":"+wordlist[i][4]+":"+wordlist[i][5]+":"+wordlist[i][6]);
		//cxt.restore();
		switch(random){
		case 0: ty+=border[3];break;
		case 1: rx+=border[2];break;
		case 2: by+=border[3];break;
		case 3: lx+=border[2];break;
		}
		}
	wordlist.sort(function(x,y){
		return y.border[2]-x.border[2];
	});
	getTowLevelBorder();
	//imgData=cxt.getImageData(0,0,cw,ch);
	//fresh();
	//editSave();
	//alert(lx+":"+rx+":"+ty+":"+by);
	compact();
}

function centerWordle(){
	cxt.clearRect(0,0,cw,ch);
	wordlist.length=0;
	switch_box=true;
	switch_Letter=true; 
	switch_Word=true;
	//var wlist = list.reverse();
	cxt.textBaseline = 'middle';
	cxt.textAlign='center';
	for(var i=0;i<len;i++){
		
		size = Math.floor((maxFontSize - minFontSize)/(max-min) * (list[i][1]-min/2) +minFontSize);
		size>1.6*maxFontSize?size=1.6*maxFontSize:'';
		cxt.font = size.toString()+"px Arial";
		//alert(size);
		color = randomColor();
		cxt.fillStyle = color;
		
		cxt.save();
		
		var wordw = Math.floor(cxt.measureText(list[i][0]).width);
		var wordh = Math.floor(cxt.measureText("Pk").width);
		
		var x = cw/2;
		var y = ch/2;
		
		var rotateRate = size/(maxFontSize+minFontSize);
		rotateRate = rotateRate>0.75?(Math.pow(rotateRate,1.6)>0.9?0.9:Math.pow(rotateRate,1.6)):(0.5+rotateRate/10+Math.pow(rotateRate,5));
		//alert(rotateRate);
		rotateDegree=Math.random()>rotateRate?(Math.random()>0.5?1:-1):0;
		//var rotateDegree=Math.random()>0.5?1:-1;;
		//alert(rotateDegree);
		//alert(x+":"+y+":"+wordw+":"+wordh); 
		/*
		if(rotateDegree){
			if(rotateDegree==1){
				(x-wordh<0)?x=wordh:'';
				(y+wordw>ch)?y=ch-wordw:'';
				//alert(x+":"+y+":"+wordw+":"+wordh); 
			}else{
				(x+wordh>cw)?x=cw-wordh:'';
				(y-wordw<0)?y=wordw:'';
				//alert(x+":"+y+":"+wordw+":"+wordh); 
			}
		}else{
			(x+wordw>cw)?x=cw-wordw:'';
			(y+wordh>ch)?y=ch-wordh:'';
			//alert(x+":"+y+":"+wordw+":"+wordh); 
		}
		*/
		//cxt.fillText(list[i][0],x,y);
		//cxt.fillRect(x,y,wordw,wordh);
		cxt.translate(x,y);
		
		cxt.rotate(rotateDegree*Math.PI/2);
		cxt.fillText(list[i][0],0,0);
		//cxt.fillRect(0,0,wordw,wordh);
		cxt.restore();
		
		//cxt.strokeRect(x-wordh,y,wordh,wordw);
		//cxt.strokeRect(x,y,wordw,wordh);
		//rotateDegree=0;
		//alert(x+":"+y+":"+wordw+":"+wordh); 
		if(rotateDegree){
			//alert(rotateDegree+" x:"+x+" y:"+y+":"+wordw+":"+wordh);
			var change;
			if(rotateDegree){
				x=Math.floor((cw-wordh)/2);
				y=Math.floor((ch-wordw)/2);
				change = wordh;
				wordh=wordw;
				wordw=change;
				//alert(x+":"+y+":"+wordw+":"+wordh); 
			}
		}else{
			x=Math.floor((cw-wordw)/2);
			y=Math.floor((ch-wordh)/2);
		}
			
		var border = getBorder(x,y,wordw,wordh);
		wordlist.push({
			word:list[i][0],
			size:size,
			font:"Arial",
			color:color,
			border:border,
			rotateDegree:rotateDegree
		});
		
		//alert(wordlist.length)
		//alert(wordlist[i][0]+":"+wordlist[i][1]+":"+wordlist[i][2]+":"+wordlist[i][3]+":"+wordlist[i][4]+":"+wordlist[i][5]+":"+wordlist[i][6]);
		//cxt.restore();
	}
	wordlist.sort(function(x,y){
		return y.border[2]-x.border[2];
	});
	getTowLevelBorder();
	//imgData=cxt.getImageData(0,0,cw,ch);
	fresh();
	editSave();
}

function rigidWordle(){
	cxt.clearRect(0,0,cw,ch);
	wordlist.length=0;
	switch_box=true;
	switch_Letter=true; 
	switch_Word=true;
	//var wlist = list.reverse();
	cxt.textBaseline = 'middle';
	cxt.textAlign='center';
	var distanceUp=0;
	var distanceDown=0;
	shuffle(list);
	for(var i=0;i<len;i++){
		
		size = Math.floor((maxFontSize - minFontSize)/(max-min) * (list[i][1]-min/2) +minFontSize);
		size>1.6*maxFontSize?size=1.6*maxFontSize:'';
		cxt.font = size.toString()+"px Arial";
		//alert(size);
		color = randomColor();
		cxt.fillStyle = color;
		
		cxt.save();
		
		var wordw = Math.floor(cxt.measureText(list[i][0]).width);
		var wordh = Math.floor(cxt.measureText("Pk").width);
		
		var x = Math.floor(cw/2);
		var y = Math.floor(ch/2);
		/*
		if(i){
			if(i%2==0){
				y = y+distanceDown+Math.ceil(wordh/2);
				//distanceDown+=wordh;
			}else{
				y = y-distanceUp-Math.ceil(wordh/2);
				//distanceUp+=wordh;
			}
		}
		*/
		if(i){
			if(i%2==0){
				y = y+distanceDown+Math.ceil(wordh/2);
				distanceDown+=wordh;
			}else{
				y = y-distanceUp-Math.ceil(wordh/2);
				distanceUp+=wordh;
			}
		}else{
			 distanceUp+=Math.ceil(wordh/2);
			 distanceDown+=Math.ceil(wordh/2);
		}
		var rotateRate = size/(maxFontSize+minFontSize);
		rotateRate = rotateRate>0.75?(Math.pow(rotateRate,1.6)>0.9?0.9:Math.pow(rotateRate,1.6)):(0.5+rotateRate/10+Math.pow(rotateRate,5));
		
		//alert(rotateRate);
		rotateDegree=Math.random()>rotateRate?(Math.random()>0.5?1:-1):0;

		cxt.fillText(list[i][0],x,y);
		//cxt.fillRect(0,0,wordw,wordh);
		cxt.restore();
		
		//cxt.strokeRect(x-wordh,y,wordh,wordw);
		//cxt.strokeRect(x,y,wordw,wordh);
		//rotateDegree=0;

		x=Math.floor((cw-wordw)/2);
		//y=Math.floor((ch-wordh)/2);
		
		if(i){
			if(i%2==0){
				y=ch/2+distanceDown-wordh;
				//y=Math.floor((ch-wordh)/2);
			}else{
				y=ch/2-distanceUp;
				//y=Math.floor((ch-wordh)/2);
			}
		}else{
			y=Math.floor((ch-wordh)/2);
		}
			
		var border = getBorder(x,y,wordw,wordh);
		wordlist.push({
			word:list[i][0],
			freq:list[i][1],
			size:size,
			font:"Arial",
			color:color,
			border:border,
			rotateDegree:rotateDegree
		});
	}
	
	wordlist.sort(function(x,y){  //以单词的宽度来排序
		return y.border[2]-x.border[2];
	});
	/*
	var info="";
	for(var i in wordlist){
		info+=wordlist[i].word+":"+wordlist[i].border[2]+"\n";
		//alert(wordlist[i].word+":"+wordlist[i].border[2]);
	}
	alert(info);
	*/
	getTowLevelBorder();
	/*
	var first=list.shift();
	shuffle(list);
	list.unshift(first);
	*/
	imgData=cxt.getImageData(0,0,cw,ch);
	//editSave();
}

function changeLayout(){
	rigidWordle();
	var lx,rx,ty,by,dx,dy;
	ty=by=0;
	for(var i=len-1;i>=0;i--){
		lx=wordlist[i].border[0];
		rx=cw-lx-wordlist[i].border[2];
		if(wordlist[i].border[1]>ch/2){
			by+=wordlist[i].border[3]+1;
			dy=ch-wordlist[i].border[1]-by;
		}else{
			dy=ty-wordlist[i].border[1];
			ty+=wordlist[i].border[3]+1;
			
		}
		dx=Math.floor(Math.min(lx,rx)*(2*(Math.random()-0.5)));
		//alert(dx+":"+dy);
		moveWord3(i,dx,dy);
	}
	//imgData=cxt.getImageData(0,0,cw,ch);
	compact();
}

function compact(){
	cxt.putImageData(imgData,0,0);
	var beforeImage=imgData;
	countCW(centerW);
	radius=1;
	inCircle.length=0; 
	outCircle.length=0;
	for(var i=0;i<len;i++){
		outCircle.push(i);
	}
	while(spiralScheme());
	//imgData=cxt.getImageData(0,0,cw,ch);
	fresh();
	for(var l=imgData.data.length,i=l/4;i<l;i++){ //只有两次改变不一样的时候才保存版本
		if(imgData.data[i]!=beforeImage.data[i]){
			//alert(imgData.data[i]+":"+beforeImage.data[i]);
			editSave();
			break;
		}
	}
	//alert("same");
	/*
	for(var ii in inCircle)
		alert(inCircle[ii].i+":"+inCircle[ii].v.length);
	*/
	//alert(radius);
}

function spiralScheme(){
	if(outCircle.length==0){
		return false;
	}
	//var newr=radius+1;
	for(var i=0,l=outCircle.length;i<l;i++){
		//var dist = EucDist(centerW[outCircle[i]].cwx,centerW[outCircle[i]].cwy,cw/2,ch/2);
		if(centerW[outCircle[i]].dist<=radius){
			var x=Math.floor(cw/2-centerW[outCircle[i]].cwx);
			var y=Math.floor(ch/2-centerW[outCircle[i]].cwy);
			reLayout(outCircle[i],x,y);
			
			var k=outCircle.splice(i,1);
			l--;
			var x1,y1,x2,y2;
			if(k<twoBoxLen){  //保存圈内的顶点
				var vv=[];
				for(var j in wlTwoBox[k].box){
					x1=wlTwoBox[k].box[j].border[0];
					y1=wlTwoBox[k].box[j].border[1];
					x2=x1+wlTwoBox[k].box[j].border[2];
					y2=y1+wlTwoBox[k].box[j].border[3];
					vv.push([x1,y1,x2,y2]);
				}
				inCircle.push({
					i:k,
					v:vv
				});
			}else{
				x1=wordlist[k].border[0];
				y1=wordlist[k].border[1];
				x2=x1+wordlist[k].border[2];
				y2=y1+wordlist[k].border[3];
				inCircle.push({
					i:k,
					v:[x1,y1,x2,y2]
				});
			}
			//alert(outCircle+"\n"+inCircle+"\n"+radius);
		}
	}
	radius++;
	return true;
}

function reLayout(i,x,y){   //x=x0-xW,y=y0-yW  x0:cw/2,y0:ch/2
	var dx,dy;
	var collision=0;
	var bL = moveWord(i,0,0); //在移动单词之前先检测该单词是否就已经发生了碰撞
	
	/*
	switch(bL){
	case 1: alert(wordlist[i].word+":1");break;
	case 2: alert(wordlist[i].word+":2");break;
	case 3: alert(wordlist[i].word+":3");break;
	case 4: alert(wordlist[i].word+":4");break;
	case 5: alert(wordlist[i].word+":5");break;
	case 6: alert(wordlist[i].word+":6");break;
	case 7: alert(wordlist[i].word+":7");break;
	case 8: alert(wordlist[i].word+":8");break;
	}
	*/
	
	while(bL){
		if(x>0&&y>0){
			bL=moveWord2(i,-1,-1);
			x++;
			y++;
		}else if(x>0&&y<0){
			bL=moveWord2(i,-1,1);
			x++;
			y--;
		}else if(x<0&&y>0){
			bL=moveWord2(i,1,-1);
			x--;
			y++;
		}else{
			bL=moveWord2(i,1,1);
			x--;
			y--;
		}
	}
	

	while((x||y)&&!collision){
		if(x==0){
			dx=0;
			dy=Math.abs(y)/y;
		}else if(y==0){
			dy=0;
			dx=Math.abs(x)/x;
		}else if(Math.abs(x)<Math.abs(y)){
			dx=Math.abs(x)/x;
			dy=2*Math.abs(y)/y;
		}else if(Math.abs(x)>Math.abs(y)){
			dx=2*Math.abs(x)/x;
			dy=Math.abs(y)/y;
		}else{
			dx=Math.abs(x)/x;
			dy=Math.abs(y)/y;
		}
		x-=dx;
		y-=dy;
		collision=moveWord(i,dx,dy);
		
		if(collision){
			if(Math.abs(x)>=Math.abs(y)){
				singleDirection(i,x,1);
				singleDirection(i,y,0);
			}else{
				singleDirection(i,y,0);
				singleDirection(i,x,1);
			}
		}
	}
}

//精确单方向移动。xy表示x或y到中心x0或y0的距离,direct为1，则为水平，0为垂直
function singleDirection(i,xy,direct){ 
	var collision;
	var dxy;
	while(xy){
		dxy=Math.abs(xy)/xy;
		xy-=dxy;
		if(direct){ //表示水平
			collision=moveWord(i,dxy,0);
		}else{ //表示垂直
			collision=moveWord(i,0,dxy);
		}
		if(collision) return true;
	}
	return false;
}

//移动第i个单词，平移的距离为dx，dy ,如果未发现碰撞，则返回0，如果发现碰撞，则返回碰撞类型（1-8）
function moveWord(i,dx,dy){ 
	//判断当前被移动的单词类型
	if(i<twoBoxLen){ //当前单词为两级盒子模型
		var wordl=wlTwoBox[i].box;
		
		var vv=[];
		for(var j in wordl){
			x1=wlTwoBox[i].box[j].border[0]+dx;
			y1=wlTwoBox[i].box[j].border[1]+dy;
			x2=x1+wlTwoBox[i].box[j].border[2];
			y2=y1+wlTwoBox[i].box[j].border[3];
			vv.push([x1,y1,x2,y2]);
		}
		for(kk in vv){
			for(var k in inCircle){
				var j=inCircle[k].i;
				if(j<twoBoxLen){  //判断圈内的单词类型
					for(var ii in inCircle[k].v){
						var ct = collisionDetect(vv[kk],inCircle[k].v[ii]);
						if(ct) return ct;
					}
				}else{
					var ct = collisionDetect(vv[kk],inCircle[k].v);
					if(ct) return ct;
				}
			}
		}
		
		for(var k in wordl){
			var xl=wordl[k].border[0]; //x_left
			var yt=wordl[k].border[1]; //x_top
			cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
		}
		wordlist[i].border[0]+=dx;
		wordlist[i].border[1]+=dy;
		wlTwoBox[i].minX+=dx;
		wlTwoBox[i].minY+=dy;
		for(var k in wordl){
			wlTwoBox[i].box[k].border[0]+=dx;
			wlTwoBox[i].box[k].border[1]+=dy;
			cxt.putImageData(wlTwoBox[i].box[k].border[4],wlTwoBox[i].box[k].border[0],wlTwoBox[i].box[k].border[1]);
		}
	}else{ //当前单词为普通盒子模型
		var x1=wordlist[i].border[0]+dx;
		var y1=wordlist[i].border[1]+dy;
		var x2=x1+wordlist[i].border[2];
		var y2=y1+wordlist[i].border[3];
		for(var k in inCircle){
			var j=inCircle[k].i;
			if(j<twoBoxLen){  //判断圈内的单词类型
				for(var ii in inCircle[k].v){
					var ct = collisionDetect([x1,y1,x2,y2],inCircle[k].v[ii]);//ct:collision type
					if(ct) return ct;
				}
			}else{
				var ct = collisionDetect([x1,y1,x2,y2],inCircle[k].v);
				if(ct) return ct;
			}
		}
		cxt.clearRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
		wordlist[i].border[0]+=dx;
		wordlist[i].border[1]+=dy;
		cxt.putImageData(wordlist[i].border[4],wordlist[i].border[0],wordlist[i].border[1]);
	}
	return 0;
}

function moveWord2(i,dx,dy){ 
	//判断当前被移动的单词类型
	if(i<twoBoxLen){ //当前单词为两级盒子模型
		var wordl=wlTwoBox[i].box;
		
		var vv=[];
		for(var j in wordl){
			x1=wlTwoBox[i].box[j].border[0]+dx;
			y1=wlTwoBox[i].box[j].border[1]+dy;
			x2=x1+wlTwoBox[i].box[j].border[2];
			y2=y1+wlTwoBox[i].box[j].border[3];
			vv.push([x1,y1,x2,y2]);
		}
		
		for(var k in wordl){
			var xl=wordl[k].border[0]; //x_left
			var yt=wordl[k].border[1]; //x_top
			cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
		}
		wordlist[i].border[0]+=dx;
		wordlist[i].border[1]+=dy;
		wlTwoBox[i].minX+=dx;
		wlTwoBox[i].minY+=dy;
		for(var k in wordl){
			wlTwoBox[i].box[k].border[0]+=dx;
			wlTwoBox[i].box[k].border[1]+=dy;
			cxt.putImageData(wlTwoBox[i].box[k].border[4],wlTwoBox[i].box[k].border[0],wlTwoBox[i].box[k].border[1]);
		}
		
		for(kk in vv){
			for(var k in inCircle){
				var j=inCircle[k].i;
				if(j<twoBoxLen){  //判断圈内的单词类型
					for(var ii in inCircle[k].v){
						var ct = collisionDetect(vv[kk],inCircle[k].v[ii]);
						if(ct) return ct;
					}
				}else{
					var ct = collisionDetect(vv[kk],inCircle[k].v);
					if(ct) return ct;
				}
			}
		}
	}else{ //当前单词为普通盒子模型
		var x1=wordlist[i].border[0]+dx;
		var y1=wordlist[i].border[1]+dy;
		var x2=x1+wordlist[i].border[2];
		var y2=y1+wordlist[i].border[3];

		cxt.clearRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
		wordlist[i].border[0]+=dx;
		wordlist[i].border[1]+=dy;
		cxt.putImageData(wordlist[i].border[4],wordlist[i].border[0],wordlist[i].border[1]);
		
		for(var k in inCircle){
			var j=inCircle[k].i;
			if(j<twoBoxLen){  //判断圈内的单词类型
				for(var ii in inCircle[k].v){
					var ct = collisionDetect([x1,y1,x2,y2],inCircle[k].v[ii]);//ct:collision type
					if(ct) return ct;
				}
			}else{
				var ct = collisionDetect([x1,y1,x2,y2],inCircle[k].v);
				if(ct) return ct;
			}
		}
	}
	return 0;
}

//判断两个盒子是否碰撞，如果未发现碰撞，则返回0，如果发现碰撞，则返回碰撞类型（1-8）
function collisionDetect(box1,box2){
	if(box1[2]>=box2[0]&&box1[2]<=box2[2]&&box1[3]>=box2[1]&&box1[3]<=box2[3]) return 1; //1右下角在2内
	if(box1[0]>=box2[0]&&box1[0]<=box2[2]&&box1[3]>=box2[1]&&box1[3]<=box2[3]) return 2; //1左下角在2内
	if(box1[2]>=box2[0]&&box1[2]<=box2[2]&&box1[1]>=box2[1]&&box1[1]<=box2[3]) return 3; //1右上角在2内
	if(box1[0]>=box2[0]&&box1[0]<=box2[2]&&box1[1]>=box2[1]&&box1[1]<=box2[3]) return 4; //1左上角在2内
	
	if(box2[2]>=box1[0]&&box2[2]<=box1[2]&&box2[3]>=box1[1]&&box2[3]<=box1[3]) return 5; //2右下角在1内
	if(box2[0]>=box1[0]&&box2[0]<=box1[2]&&box2[3]>=box1[1]&&box2[3]<=box1[3]) return 6; //2左下角在1内
	if(box2[2]>=box1[0]&&box2[2]<=box1[2]&&box2[1]>=box1[1]&&box2[1]<=box1[3]) return 7; //2右上角在1内
	if(box2[0]>=box1[0]&&box2[0]<=box1[2]&&box2[1]>=box1[1]&&box2[1]<=box1[3]) return 8; //2左上角在1内
	return 0;
}

function EucDist(x1,y1,x2,y2){
	return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
}

function countCW(centerW){
	centerW.length=0;
	var cwx;
	var cwy;
	var dist;
	for(var i=0;i<len;i++){
		if(i<twoBoxLen){
			cwx=Math.ceil(wlTwoBox[i].minX+wlTwoBox[i].minW/2);
			cwy=Math.floor(wlTwoBox[i].minY+wlTwoBox[i].minH/2);
		}else{
			cwx=Math.ceil(wordlist[i].border[0]+wordlist[i].border[2]/2);
			cwy=Math.floor(wordlist[i].border[1]+wordlist[i].border[3]/2);
		}
		dist=EucDist(cwx,cwy,cw/2,ch/2);
		centerW.push({
			cwx:cwx,
			cwy:cwy,
			dist:dist
		});
	}
	/*
	var str="";
	for(var i=0;i<len;i++){
		str+=centerW[i].cwx+":"+centerW[i].cwy+"\n";
	}
	alert(str);
	*/
}

function moveWord3(i,dx,dy){ 
	//判断当前被移动的单词类型
	if(i<twoBoxLen){ //当前单词为两级盒子模型
		var wordl=wlTwoBox[i].box;
		/*
		var vv=[];
		for(var j in wordl){
			x1=wlTwoBox[i].box[j].border[0]+dx;
			y1=wlTwoBox[i].box[j].border[1]+dy;
			x2=x1+wlTwoBox[i].box[j].border[2];
			y2=y1+wlTwoBox[i].box[j].border[3];
			vv.push([x1,y1,x2,y2]);
		}
		for(kk in vv){
			for(var k in inCircle){
				var j=inCircle[k].i;
				if(j<twoBoxLen){  //判断圈内的单词类型
					for(var ii in inCircle[k].v){
						var ct = collisionDetect(vv[kk],inCircle[k].v[ii]);
						if(ct) return ct;
					}
				}else{
					var ct = collisionDetect(vv[kk],inCircle[k].v);
					if(ct) return ct;
				}
			}
		}
		*/
		for(var k in wordl){
			var xl=wordl[k].border[0]; //x_left
			var yt=wordl[k].border[1]; //x_top
			cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
		}
		wordlist[i].border[0]+=dx;
		wordlist[i].border[1]+=dy;
		wlTwoBox[i].minX+=dx;
		wlTwoBox[i].minY+=dy;
		for(var k in wordl){
			wlTwoBox[i].box[k].border[0]+=dx;
			wlTwoBox[i].box[k].border[1]+=dy;
			cxt.putImageData(wlTwoBox[i].box[k].border[4],wlTwoBox[i].box[k].border[0],wlTwoBox[i].box[k].border[1]);
		}
	}else{ //当前单词为普通盒子模型
		/*
		var x1=wordlist[i].border[0]+dx;
		var y1=wordlist[i].border[1]+dy;
		var x2=x1+wordlist[i].border[2];
		var y2=y1+wordlist[i].border[3];
		
		for(var k in inCircle){
			var j=inCircle[k].i;
			if(j<twoBoxLen){  //判断圈内的单词类型
				for(var ii in inCircle[k].v){
					var ct = collisionDetect([x1,y1,x2,y2],inCircle[k].v[ii]);//ct:collision type
					if(ct) return ct;
				}
			}else{
				var ct = collisionDetect([x1,y1,x2,y2],inCircle[k].v);
				if(ct) return ct;
			}
		}
		*/
		cxt.clearRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
		wordlist[i].border[0]+=dx;
		wordlist[i].border[1]+=dy;
		cxt.putImageData(wordlist[i].border[4],wordlist[i].border[0],wordlist[i].border[1]);
	}
	return 0;
}

function showWord(i){
	cxt.putImageData(imgData,0,0);
	cxt.strokeStyle="red";
	//console.log(wordlist[i].border[0]);
	cxt.strokeRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
}

function showSelectWords(i){
	//cxt.putImageData(imgData,0,0);
	cxt.strokeStyle="red";
	cxt.strokeRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
}

function showBox(color){
	cxt.putImageData(imgData,0,0);
	if(switch_box){
		//imgData=cxt.getImageData(0,0,cw,ch);
    	cxt.strokeStyle=color;
    	for(var i=0;i<len;i++){
    		cxt.strokeRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
    	}
    	switch_box=false;
	}else{
		//cxt.clearRect(0,0,cw,ch);
		//cxt.putImageData(imgData,0,0);
		switch_box=true; 
	}
	/*
	if(switch_Letter){
		Letter_levelBox("red");
	}
	if(switch_Word){
		Word_levelBox("black");
	}
	*/
	/*
	cxt.save();
	cxt.strokeStyle=color;
	for(var i=0;i<wordlist.length;i++){
		cxt.strokeRect(wordlist[i][3],wordlist[i][4],wordlist[i][5],wordlist[i][6]);
	}
	setTimeout(function(){
		alert("3");
		cxt.clearRect(0,0,cw,ch);
		//cxt.restore();
		cxt.putImageData(imgData,0,0);
	},3000);
	*/
}

function showTwoBox(color1,color2){
	if(switch_TwoBox){
		
		//imgData=cxt.getImageData(0,0,cw,ch);
		
		//cxt.setLineDash([8,5]);
		//cxt.setLineWidth=1;
		//alert(wordlistTwoBox.length)
    	for(var i=0;i<twoBoxLen;i++){ //画每个单词
    		cxt.save();
    		cxt.strokeStyle=color1;
    		var lBorder=wlTwoBox[i].box;
    		for(var j in lBorder){ //画每个单词中的每个字母
    			cxt.strokeRect(lBorder[j].border[0],lBorder[j].border[1],lBorder[j].border[2],lBorder[j].border[3]);
    		}
    		//alert(wordlistTwoBox[i].box[0].border[0])
    		cxt.strokeStyle=color2;
    		cxt.setLineDash([8,5]);
    		cxt.setLineWidth=1;
    		cxt.strokeRect(wlTwoBox[i].minX,wlTwoBox[i].minY,wlTwoBox[i].minW,wlTwoBox[i].minH);
        	cxt.restore();
    	}
    	switch_TwoBox=false;
	}else{
		cxt.putImageData(imgData,0,0);
		switch_TwoBox=true; 
	}
	
	/*
	var wordTwoBox;
	for(var i=0;i<len;i++){
		wordTwoBox[0] = wordlist[i];
		alert(wordlistTwoBox.length)
		var imageData=wordlist[i][7];
	}
	*/
}

function Word_levelBox(color){
	if(switch_Word){
		
    	for(var i=0;i<twoBoxLen;i++){ //画每个单词
    		cxt.save();
    		cxt.strokeStyle=color;
    		cxt.setLineDash([8,5]);
    		cxt.setLineWidth=1;
    		cxt.strokeRect(wlTwoBox[i].minX,wlTwoBox[i].minY,wlTwoBox[i].minW,wlTwoBox[i].minH);
        	cxt.restore();
    	}
    	switch_Word=false;
	}else{
		cxt.putImageData(imgData,0,0);
		switch_Word=true; 
		/*
		if(switch_Letter){
			Letter_levelBox("red");
		}
		if(switch_box){
			showBox("red");
		}
		*/
	}
}

function Letter_levelBox(color){
	if(switch_Letter){
    	for(var i=0;i<twoBoxLen;i++){ //对于每个单词
    		cxt.save();
    		cxt.strokeStyle=color;
    		var lBorder=wlTwoBox[i].box;
    		for(var j in lBorder){ //画每个单词中的每个字母
    			cxt.strokeRect(lBorder[j].border[0],lBorder[j].border[1],lBorder[j].border[2],lBorder[j].border[3]);
    		}
    	}
    	switch_Letter=false;
	}else{
		cxt.putImageData(imgData,0,0);
		switch_Letter=true; 
		/*
		if(switch_Word){
			Word_levelBox("black");
		}
		if(switch_box){
			showBox("red");
		}
		*/
	}
}

function getTowLevelBorder(){

	wlTwoBox.length=0;
	for(var i=0;i<twoBoxLen;i++){
		var letterTotalBorder=[];//每个单词所有字母的边界
    	var border=wordlist[i].border;
    	//alert(border+":size:"+wordlist[0].size);
    	var word=wordlist[i].word;
    	//alert(word.length+":"+word[0]);
    	//var size=wordlist[i].size;

		cxt.textBaseline = 'top';
		cxt.textAlign='start';
    	cxt.font = wordlist[i].size.toString()+"px "+wordlist[i].font;
    	var x=border[0];
    	var y=border[1];
		var dw=border[2];
		//cxt.fillText(word[0],x,y);
		//var wordw=Math.floor(cxt.measureText(word[0]).width);
		var wordh=border[3];
		//cxt.strokeRect(x,y,wordw,wordh);
		//var letterBorder=getBorder2(x,y,wordw,wordh);
		//cxt.strokeRect(letterBorder[0],letterBorder[1],letterBorder[2],letterBorder[3]);
    	var minH=wordh;
    	var minY=y;
		for(var j in word){
    		//alert(word[i]);
    		if(word[j]==' '){
    			continue;
    		}
    		cxt.fillText(word[j],0,0);
    		var wordw=Math.floor((cxt.measureText(word[j]).width)); //注意，像素一定要是整数，要不然会出错
    		var lettersize=getBorder(0,0,wordw,Math.floor(wordh*1.5));
    		cxt.clearRect(0,0,wordw,wordh*2);
    		wordw=lettersize[2];
    		//var wordh=border[3];

    		var letterBorder=getBorder(x,y,wordw+1,wordh);
    		
    		//cxt.strokeRect(letterBorder[0],letterBorder[1],letterBorder[2],letterBorder[3]);
    		//x+=letterBorder[0]+1;
    		x=letterBorder[0]+letterBorder[2]+1;
    		x=getBorder(x,y,dw-letterBorder[2],wordh)[0];
    		if(j==(word.length-1)){
    			letterBorder[2]=border[2]-(letterBorder[0]-border[0]);
    		}
    		letterTotalBorder.push({
    			letter:word[j],
    			border:letterBorder
    		});
    		if(minH>letterBorder[3]){
    			minH=letterBorder[3];
    			minY=letterBorder[1];
    		}
    		
    	}
    	wlTwoBox.push({
			word:word,
			box:letterTotalBorder,
			minX:border[0],
			minY:minY,
			minW:border[2],
			minH:minH
		});
	}
	//alert("wo skadhask")
}

function getTowLevelBorder2(i){
	var letterTotalBorder=[];//每个单词所有字母的边界
	var border=wordlist[i].border;
	//alert(border+":size:"+wordlist[0].size);
	var word=wordlist[i].word;
	//alert(word.length+":"+word[0]);
	//var size=wordlist[i].size;

	cxt.textBaseline = 'top';
	cxt.textAlign='start';
	cxt.font = wordlist[i].size.toString()+"px "+wordlist[i].font;
	var x=border[0];
	var y=border[1];
	var dw=border[2];
	//cxt.fillText(word[0],x,y);
	//var wordw=Math.floor(cxt.measureText(word[0]).width);
	var wordh=border[3];
	//cxt.strokeRect(x,y,wordw,wordh);
	//var letterBorder=getBorder2(x,y,wordw,wordh);
	//cxt.strokeRect(letterBorder[0],letterBorder[1],letterBorder[2],letterBorder[3]);
	var minH=wordh;
	var minY=y;
	for(var j in word){
		//alert(word[i]);
		if(word[j]==' '){
			continue;
		}
		cxt.fillText(word[j],0,0);
		var wordw=Math.floor((cxt.measureText(word[j]).width)); //注意，像素一定要是整数，要不然会出错
		var lettersize=getBorder(0,0,wordw,Math.floor(wordh*1.5));
		cxt.clearRect(0,0,wordw,wordh*2);
		wordw=lettersize[2];
		//var wordh=border[3];

		var letterBorder=getBorder(x,y,wordw+1,wordh);
		
		//cxt.strokeRect(letterBorder[0],letterBorder[1],letterBorder[2],letterBorder[3]);
		//x+=letterBorder[0]+1;
		x=letterBorder[0]+letterBorder[2]+1;
		x=getBorder(x,y,dw-letterBorder[2],wordh)[0];
		if(j==(word.length-1)){
			letterBorder[2]=border[2]-(letterBorder[0]-border[0]);
		}
		letterTotalBorder.push({
			letter:word[j],
			border:letterBorder
		});
		if(minH>letterBorder[3]){
			minH=letterBorder[3];
			minY=letterBorder[1];
		}
		
	}
	wlTwoBox[i].box=letterTotalBorder;
	wlTwoBox[i].minX=border[0];
	wlTwoBox[i].minY=minY;
	wlTwoBox[i].minW=border[2];
	wlTwoBox[i].minH=minH;
}

/*
function getTowLevelBorder(){
	wordlistTwoBox.length=0;
	for(var i=0;i<twoBoxLen;i++){
		var letterTotalBorder=[];//每个单词所有字母的边界
    	var border=wordlist[i].border;
    	//alert(border+":size:"+wordlist[0].size);
    	var word=wordlist[i].word;
    	//alert(word.length+":"+word[0]);
    	//var size=33;
    	cxt.font = wordlist[i].size.toString()+"px "+wordlist[i].font;
    	var x=border[0];
    	var y=border[1];
		
		//cxt.fillText(word[0],x,y);
		//var wordw=Math.floor(cxt.measureText(word[0]).width);
		var wordh=border[3];
		//cxt.strokeRect(x,y,wordw,wordh);
		//var letterBorder=getBorder2(x,y,wordw,wordh);
		//cxt.strokeRect(letterBorder[0],letterBorder[1],letterBorder[2],letterBorder[3]);
    	var minH=wordh;
    	var minY=y;
		for(var j in word){
    		//alert(word[i]);
    		if(word[j]==' '){
    			continue;
    		}
    		var wordw=Math.floor(cxt.measureText(word[j]).width); //注意，像素一定要是整数，要不然会出错

    		var letterBorder=getBorder2(x,y,wordw,wordh);
    		
    		//cxt.strokeRect(letterBorder[0],letterBorder[1],letterBorder[2],letterBorder[3]);
    		//x+=letterBorder[0]+1;
    		x=letterBorder[0]+letterBorder[2]+1;
    		if(j==(word.length-1)){
    			letterBorder[2]=border[2]-(letterBorder[0]-border[0]);
    		}
    		letterTotalBorder.push({
    			letter:word[j],
    			border:letterBorder
    		});
    		if(minH>letterBorder[3]){
    			minH=letterBorder[3];
    			minY=letterBorder[1];
    		}
    	}
    	wordlistTwoBox.push({
			word:word,
			box:letterTotalBorder,
			minX:border[0],
			minY:minY,
			minW:border[2],
			minH:minH
		});
	}
	//alert("wo skadhask")
}
*/

function shuffle(a) {
	var len=a.length;
    for (var i = 0; i < len - 1; i++) {
        var index = parseInt(Math.random() * (len - i));
        var temp = a[index];
        a[index] = a[len - i - 1];
        a[len - i - 1] = temp;
    }
}

function getBorder(x,y,wordw,wordh){
	var imageData;
	imageData = cxt.getImageData(x,y,wordw,wordh);
	//cxt.strokeStyle="blue";
	//cxt.strokeRect(x,y,wordw,wordh);
	//alert(x+":"+y+":"+wordw+":"+wordh)
	//alert(x+":"+y+":"+wordw+":"+wordh);
	//alert("imageData.data.length:"+imageData.data.length);
	//alert(wordw+":"+wordh);
	//alert("y:"+y+"  wordh:"+wordh);
	var h=wordh;
	var yyy=0;
	touterloop:
	for(var j=0;j<h;j++){
		for(var k=0;k<wordw;k++){
			var item= (j*wordw+k)*4;
			if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){
				break touterloop;
			}
		}
		wordh--;
		y++;
		yyy++;
	}
	bouterloop:
	for(var j=h-1;j>0;j--){
		for(var k=0;k<wordw;k++){
			var item= (j*wordw+k)*4;
			if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){
				//alert(imageData.data[65*wordw*4])
				//alert(yyy+":"+j+":"+k)
				break bouterloop;
			}
		}
		wordh--;
	}
	//alert(yyy);
	var w=wordw;
	louterloop:
	for(var j=0;j<w;j++){
		for(var k=yyy;k<wordh+yyy;k++){
			//alert(j*w*k*4);
			var item=(j+w*k)*4;
			//alert(item+":"+imageData.date.length);
			if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){
				break louterloop;
			}
			
		}
		wordw--;
		x++;
	}
	//alert(wordw);
	routerloop:
		for(var j=w-1;j>0;j--){
			for(var k=yyy;k<wordh+yyy;k++){
				//alert(j*w*k*4);
				var item=(j+w*k)*4;
				//alert(item+":"+imageData.date.length);
				if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){
					//alert(j);
					break routerloop;
				}
				
			}
			wordw--;
		}
	imageData = cxt.getImageData(x,y,wordw,wordh);//注意：如果参数值小于等于0，则程序运行出错
	//alert(x+":"+y+":"+wordw+":"+wordh)
	//alert(wordw+":"+wordh);
	//cxt.strokeStyle="red";
	//cxt.strokeRect(x,y,wordw,wordh);
	return [x,y,wordw,wordh,imageData];
	//wordlist.push([word,size,color,x,y,wordw,wordh,imageData,rotateDegree]);
}

function getBorder2(x,y,wordw,wordh){
	var imageData;
	imageData = cxt.getImageData(x,y,wordw,wordh);
	cxt.strokeStyle="blue";
	//cxt.strokeRect(x,y,wordw,wordh);
	var h=wordh;
	var yyy=0;
	touterloop:
	for(var j=0;j<h;j++){
		for(var k=0;k<wordw;k++){
			var item= (j*wordw+k)*4;
			if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){
				break touterloop;
			}
		}
		wordh--;
		y++;
		yyy++;
	}
	bouterloop:
	for(var j=h-1;j>0;j--){
		for(var k=0;k<wordw;k++){
			var item= (j*wordw+k)*4;
			if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){

				break bouterloop;
			}
		}
		wordh--;
	}
	//alert(yyy);
	var w=wordw;
	var xxx=0;
	louterloop:
	for(var j=0;j<w;j++){
		for(var k=yyy;k<wordh+yyy;k++){
			//alert(j*w*k*4);
			var item=(j+w*k)*4;
			//alert(item+":"+imageData.date.length);
			if(imageData.data[item]||imageData.data[item+1]||imageData.data[item+2]||imageData.data[item+3]){
				break louterloop;
			}
			
		}
		//wordw--;
		x++;
		xxx++;
		//alert("bottom");
	}
	//alert(wordw);
	routerloop:
		for(var j=xxx+1;j<w;j++){
			var col=0;
			for(var k=yyy;k<wordh+yyy;k++){
				//alert(j*w*k*4);
				var item=(j+w*k)*4;
				//alert(item+":"+imageData.date.length);
				if(imageData.data[item]+imageData.data[item+1]+imageData.data[item+2]+imageData.data[item+3]==0){
					//alert(j);
					//break routerloop;
					col++;
				}
				
			}
			if(col>=wordh){
				wordw=j-xxx;
				//alert(wordw+":"+wordh);
				break;
			}
			//wordw--;
		}
	imageData = cxt.getImageData(x,y,wordw,wordh);//注意：如果参数值小于等于0，则程序运行出错
	//alert(x+":"+y+":"+wordw+":"+wordh)
	//alert(wordw+":"+wordh);
	return [x,y,wordw,wordh,imageData];
	//wordlist.push([word,size,color,x,y,wordw,wordh,imageData,rotateDegree]);
}

function randomColor(){
  	var hex = Math.floor(Math.random()*16777216).toString(16);
  	while(hex.length<6){
  		hex = '0' + hex;
  	}
  	return '#'+hex;
}

function changeWordColor(color,i){
	//alert(wordlist[i].word+":"+wordlist[i].size);
	cxt.putImageData(imgData,0,0);
	cxt.textBaseline = 'top';
	cxt.textAlign='start';
	cxt.font = wordlist[i].size.toString()+"px "+wordlist[i].font;
	wordlist[i].color = cxt.fillStyle = color;
	cxt.fillText(wordlist[i].word,0,450);
	var wordw=Math.floor(cxt.measureText(wordlist[i].word).width); //注意，像素一定要是整数，要不然会出错
	var newWBorder = getBorder(0,450,wordw,Math.floor(wordlist[i].border[3]*1.5));
	//cxt.clearRect(0,450,wordw,Math.floor(wordlist[i].border[3]*1.5));
	//cxt.strokeRect(newWBorder[0],newWBorder[1],newWBorder[2],newWBorder[3]);
	wordlist[i].border[4] = newWBorder[4];
	if(i<twoBoxLen){
		//alert(wordlist[i].border);
		var wordl=wlTwoBox[i].box;
		for(var k in wordl){
			var xl=wordl[k].border[0]; //x_left
			var yt=wordl[k].border[1]; //x_top
			cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
		}
		var wx=wordlist[i].border[0];
		var wy=wordlist[i].border[1];
		
		wordlist[i].border[0] = newWBorder[0];
		wordlist[i].border[1] = newWBorder[1];
		//wordlist[i].border[2] = newWBorder[2];
		//wordlist[i].border[3] = newWBorder[3];
		getTowLevelBorder2(i);
		var dx=wx-newWBorder[0];
		var dy=wy-newWBorder[1];
		
		wordlist[i].border[0]=wx;
		wordlist[i].border[1]=wy;
		wlTwoBox[i].minX+=dx;
		wlTwoBox[i].minY+=dy;
		for(var k in wordl){
			wlTwoBox[i].box[k].border[0]+=dx;
			wlTwoBox[i].box[k].border[1]+=dy;
			cxt.putImageData(wlTwoBox[i].box[k].border[4],wlTwoBox[i].box[k].border[0],wlTwoBox[i].box[k].border[1]);
		}
		//alert(wordlist[i].border);
	}else{
		cxt.putImageData(newWBorder[4],wordlist[i].border[0],wordlist[i].border[1]);
		
	}
	cxt.clearRect(0,450,wordw,Math.floor(wordlist[i].border[3]*1.5));
	//imgData=cxt.getImageData(0,0,cw,ch);
	fresh();
	editSave();
	showWord(i);
}

function changeWordFont(fontType,i){
	//alert(fontType);
	cxt.putImageData(imgData,0,0);
	cxt.textBaseline = 'top';
	cxt.textAlign='start';
	cxt.font = wordlist[i].size.toString()+"px "+fontType;
	cxt.fillStyle = wordlist[i].color;
	cxt.fillText(wordlist[i].word,0,450);
	var wordw=Math.floor(cxt.measureText(wordlist[i].word).width); //注意，像素一定要是整数，要不然会出错
	var newWBorder = getBorder(0,450,wordw,wordlist[i].border[3]*2);
	//cxt.clearRect(0,450,wordw,wordlist[i].border[3]*2);
	wordlist[i].border[2] = newWBorder[2];
	wordlist[i].border[3] = newWBorder[3];
	wordlist[i].border[4] = newWBorder[4];
	wordlist[i].font=fontType;
	if(i<twoBoxLen){
		//alert(wordlist[i].border);
		var wordl=wlTwoBox[i].box;
		for(var k in wordl){
			var xl=wordl[k].border[0]; //x_left
			var yt=wordl[k].border[1]; //x_top
			cxt.clearRect(xl,yt,wordl[k].border[2],wordl[k].border[3]);
		}
		var wx=wordlist[i].border[0];
		var wy=wordlist[i].border[1];
		
		wordlist[i].border[0] = newWBorder[0];
		wordlist[i].border[1] = newWBorder[1];
		getTowLevelBorder2(i);
		var dx=wx-newWBorder[0];
		var dy=wy-newWBorder[1];
		
		wordlist[i].border[0]=wx;
		wordlist[i].border[1]=wy;
		wlTwoBox[i].minX+=dx;
		wlTwoBox[i].minY+=dy;
		for(var k in wordl){
			wlTwoBox[i].box[k].border[0]+=dx;
			wlTwoBox[i].box[k].border[1]+=dy;
			cxt.putImageData(wlTwoBox[i].box[k].border[4],wlTwoBox[i].box[k].border[0],wlTwoBox[i].box[k].border[1]);
		}
		//alert(wordlist[i].border);
	}else{
		cxt.clearRect(wordlist[i].border[0],wordlist[i].border[1],wordlist[i].border[2],wordlist[i].border[3]);
		cxt.putImageData(newWBorder[4],wordlist[i].border[0],wordlist[i].border[1]);
		
	}
	cxt.clearRect(0,450,wordw,wordlist[i].border[3]*2);
	//imgData=cxt.getImageData(0,0,cw,ch);
	fresh();
	editSave();
	//compact();
	showWord(i);
}

function fresh(){
	cxt.clearRect(0,0,cw,ch);
	for(var i in wordlist){
		if(i<twoBoxLen){
			for(var j in wlTwoBox[i].box){
				cxt.putImageData(wlTwoBox[i].box[j].border[4],wlTwoBox[i].box[j].border[0],wlTwoBox[i].box[j].border[1]);
			}
		}else{
			cxt.putImageData(wordlist[i].border[4],wordlist[i].border[0],wordlist[i].border[1]);
		}	
	}
	imgData=cxt.getImageData(0,0,cw,ch);
	var switch_box=true; //外边框开关
	var switch_Letter=true; //字母边框开关
	var switch_Word=true; //单词边框开关
}

function editSave(){
	var wl=new Array();
	var wltb=new Array();
	/*
	 * var str="";
	for(var i in wordlist){
		str=JSON.stringify(wordlist[i]);
		wl.push(JSON.parse(str));
	}
	
	for(var i in wlTwoBox){
		str=JSON.stringify(wlTwoBox[i]);
		wltb.push(JSON.parse(str));
	}
	
	*/
	deepClone(wl,wordlist,1);
	deepClone(wltb,wlTwoBox,0);
	
	if(editVI==editVersion.length-1){ //如果当前版本是最新版本，则直接将改动的版本添加到editVersion尾部
		editVersion.push({
			wordlist:wl,
			wlTwoBox:wltb,
			imgData:imgData
		});
		editVI++;
	}else{ //否则清空该版本之后的版本
		editVersion.length=++editVI;
		editVersion.push({
			wordlist:wl,
			wlTwoBox:wltb,
			imgData:imgData
		});
	}
	
	/*
	alert(editVersion.length+"\n"+editVI);
	for(var i in editVersion){
		alert(editVersion[i].wordlist[0].border);
	}
	*/
	/*
	if(editVI==editVersion.length-1){ //如果当前版本是最新版本，则直接将改动的版本添加到editVersion尾部
		editVersion.push([wordlist,wlTwoBox,imgData]);
		editVI++;
	}else{ //否则清空该版本之后的版本
		editVersion.length=++editVI;
		editVersion.push([wordlist,wlTwoBox,imgData]);
	}
	*/
}

function undo(){
	if(editVI==0){
		alert("No more PreEdit information!");
	}else{
		editVI--;
		
		wordlist.length=0;
		wlTwoBox.length=0;
		/*
		var str="";
		for(var i in editVersion[editVI].wordlist){
			str=JSON.stringify(editVersion[editVI].wordlist[i]);
			wordlist.push(JSON.parse(str));
		}
		*/
		deepClone(wordlist,editVersion[editVI].wordlist,1);
		deepClone(wlTwoBox,editVersion[editVI].wlTwoBox,0);
		/*
		for(var i in editVersion[editVI].wlTwoBox){
			str=JSON.stringify(editVersion[editVI].wlTwoBox[i]);
			wlTwoBox.push(JSON.parse(str));
		}
		*/
		imgData = editVersion[editVI].imgData;
		/*
		wordlist = editVersion[editVI][0];
		wlTwoBox = editVersion[editVI][1];
		imgData = editVersion[editVI][2];
		*/
		cxt.putImageData(imgData,0,0);
	}
}

function redo(){
	if(editVI==editVersion.length-1){
		alert("No more AfterEdit information!");
	}else{
		editVI++;
		wordlist.length=0;
		wlTwoBox.length=0;
		deepClone(wordlist,editVersion[editVI].wordlist,1);
		deepClone(wlTwoBox,editVersion[editVI].wlTwoBox,0);
		/*
		wordlist = editVersion[editVI].wordlist;
		wlTwoBox = editVersion[editVI].wlTwoBox;
		imgData = editVersion[editVI].imgData;
		*/
		/*
		wordlist = editVersion[editVI][0];
		wlTwoBox = editVersion[editVI][1];
		imgData = editVersion[editVI][2];
		*/
		imgData = editVersion[editVI].imgData;
		cxt.putImageData(imgData,0,0);
	}
}

function deepClone(d,s,type){ //1:wordlist  0:wlTwoBox   深复制（深拷贝）
	if(type){
		for(var i in s){
			var border=[];
			for(var j in s[i].border){
				border.push(s[i].border[j]);
			}
			d.push({
				word:s[i].word,
				freq:s[i].freq,
				size:s[i].size,
				font:s[i].font,
				color:s[i].color,
				border:border,
				rotateDegree:s[i].rotateDegree
			});
		}
	}else{
		for(var i in s){ //表示有i个单词
			var lTotalBorder=[];
			for(var j in s[i].box){//表示每个单词有j个字母
				/*
				var border=[];
				for(var k in s[i].box[j]){
				//for(var k=0;k<5;i++)
					var xx= s[i].box[j].border[k];
					border.push(xx);  //数据类型不一致
				}
				alert(s[i].box[j].border[0]+"\n"+border[0]);
				*/
				var xx= s[i].box[j].border[0];
				//alert(xx+":"+s[i].box[j].border[4]);
				lTotalBorder.push({
					letter:s[i].box[j].letter,
					border:[s[i].box[j].border[0],s[i].box[j].border[1],s[i].box[j].border[2],s[i].box[j].border[3],s[i].box[j].border[4]]
				});
			}
			d.push({
				word:s[i].word,
				box:lTotalBorder,
				minX:s[i].minX,
				minY:s[i].minY,
				minW:s[i].minW,
				minH:s[i].minH,
			});
		}
		//alert(s[0].box[0].border[0]+"\n"+d[0].box[0].border[0]);
	}//-else
}

function WordsList(){
	list.sort(function(x,y){
		return y[1]-x[1];
	});
	var str="Words List\nword : frequency\n";
	/*
	var sum=0;
	for(var i in list){
		sum+=list[i][1];
	}
	var weight=[];  //计算权重
	for(var i in list){
		var w=list[i][1]/sum;
		weight.push(w.toFixed(2));
	}
	*/
	for(var i=0;i<len;i++){
		str=str+list[i][0]+" : "+list[i][1]+"\n";
		//str=str+list[i][0]+" : "+list[i][1]+" : "+weight[i]+"\n";
	}
	alert(str);
}

function download(type) {
    //设置保存图片的类型
    var imgdata = canvas.toDataURL(type);
    //将mime-type改为image/octet-stream,强制让浏览器下载
    var fixtype = function (type) {
        type = type.toLocaleLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    }
    imgdata = imgdata.replace(fixtype(type), 'image/octet-stream')
    //将图片保存到本地
    var saveFile = function (data, filename) {
        var link = document.createElement('a');
        link.href = data;
        link.download = filename;
        var event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        link.dispatchEvent(event);
    }
    var timestamp=new Date().getTime(); //new Date().toLocaleDateString();
    var filename = timestamp + '.' + type;
    saveFile(imgdata, filename);
}