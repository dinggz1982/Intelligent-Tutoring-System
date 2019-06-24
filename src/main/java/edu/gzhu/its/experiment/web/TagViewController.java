package edu.gzhu.its.experiment.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.experiment.entity.MyWord;
import edu.gzhu.its.experiment.entity.Topic;
import edu.gzhu.its.experiment.entity.Word;
import edu.gzhu.its.experiment.model.WordModel;
import edu.gzhu.its.experiment.service.IMyWordService;
import edu.gzhu.its.experiment.service.ITagEditHistoryService;
import edu.gzhu.its.experiment.service.ITopicService;
import edu.gzhu.its.experiment.service.IWordService;
import edu.gzhu.its.system.entity.User;

@Controller
public class TagViewController {
	@Autowired
	private HttpSession session;
	@Autowired
	private IWordService wordService;
	@Autowired
	private IMyWordService myWordService;
	@Autowired
	private ITopicService topicService;
	@Autowired
	private ITagEditHistoryService tagEditHistoryService;
	
	@GetMapping("/tagView")
	public String tagView(Model model) throws SQLException{
		
		List<Word> words = this.wordService.findAll();
		String wordString = "['学习',467,'#33ff00',10,20],['学习动机',426,'#33ff00',40,50],['学生',246,'#33ff00',50,70],['知觉',122,'#33ff00',200,100],['动机',122,'#33ff00',400,200],['坚持',110,'#33ff00',150,90]";
		model.addAttribute("wordString", wordString);
		//["Layout",846]
		for (Iterator iterator = words.iterator(); iterator.hasNext();) {
			Word word = (Word) iterator.next();
			//wordString
		}
		
		
		return "tagview/index";
	}
	
	@GetMapping("/tagTest")
	public String tagTest(Model model) throws SQLException{
		List<Word> words = this.wordService.findAll();
		String wordString = "['学习',467,'#33ff00',10,20],['学习动机',426,'#33ff00',40,50],['学生',246,'#33ff00',50,70],['知觉',122,'#33ff00',200,100],['动机',122,'#33ff00',400,200],['坚持',110,'#33ff00',150,90]";
		model.addAttribute("wordString", wordString);
		//["Layout",846]
		for (Iterator iterator = words.iterator(); iterator.hasNext();) {
			Word word = (Word) iterator.next();
			//wordString
		}
		return "tagview/tagTest";
	}
	/**
	 * 保存标签
	 * @return
	 */
	@PostMapping("/saveMytag")
	@ResponseBody
	public String saveMytag(String wordModels){
		//获取当前用户
		User currentUser=  (User) session.getAttribute("currentUser");
		JSONArray myJsonArray = JSONArray.parseArray(wordModels);
		System.out.println(myJsonArray.size());
		List<MyWord> myWords = new LinkedList<MyWord>();
		for (Iterator iterator = myJsonArray.iterator(); iterator.hasNext();) {
			
			JSONObject jsonObject = (JSONObject) iterator.next();
			/*System.out.println(jsonObject.get("word"));
			System.out.println(jsonObject.get("border"));
			System.out.println(jsonObject.get("font"));
			System.out.println(jsonObject.get("size"));
			System.out.println(jsonObject.get("color"));*/

			MyWord myWord = new MyWord();
			String[] borders = jsonObject.get("border").toString().split("\\{");
			String res = borders[0].substring(1,borders[0].length()-1);
			myWord.setBorder(res);
			myWord.setColor(jsonObject.get("color").toString());
			myWord.setFont(jsonObject.get("font").toString());
			Word word =new Word();
			word.setId(1);
			//myWord.setWord(word);
			myWord.setStr(jsonObject.get("word").toString());
			myWord.setCreateTime(new Date());
			myWord.setSize(jsonObject.get("size").toString());
			myWord.setUser(currentUser);
			myWords.add(myWord);
		}
        this.myWordService.batchSave(myWords);
		return "";
	}
	

	/**
	 * 设定标签
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/tag-setting")
	public String tagSetting(Integer pageIndex, Integer pageSize,Model model) throws SQLException{
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Topic> pageData = this.topicService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "/tagview/topList";
	}
	
	
	@GetMapping("/tag/topic/{id}")
	public String topicDetail(Integer pageIndex, Integer pageSize,Model model,@PathVariable Integer id) throws SQLException{
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Word> pageData = this.wordService.getPageData(pageIndex, pageSize, " and topic_id="+id);
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("topic_id", id);
		return "/tagview/wordList";
	}
	
	@GetMapping("/tag/editTag/{id}")
	public String editTag(@PathVariable int id,Model model){
		User currentUser=  (User) session.getAttribute("currentUser");
		List<MyWord> wordString = this.myWordService.find(" where user_id="+currentUser.getId()+" and topic_id="+id);
		int isTaged = 0;
		if(wordString!=null&&wordString.size()>0) {
			 isTaged = 1;
			model.addAttribute("wordList", wordString);
		}
		else {
			List<Word> wordList = this.wordService.find(" where topic_id="+id);
			List<WordModel> wordModels = new ArrayList<WordModel>();
			int i=1;
			for (Iterator iterator = wordList.iterator(); iterator.hasNext();) {
				Word word = (Word) iterator.next();
				WordModel model2 = new WordModel();
				model2.setColor("#000000");
				model2.setWord(word.getWord());
				model2.setPositionX(400);
				model2.setPositionY(30*i);
				model2.setSize("20");
				wordModels.add(model2);
				i=i+2;
			}
			model.addAttribute("wordList", wordModels);
		}
		return "/tagview/editTag";
	}
	
	
	@GetMapping("/tag/add")
	public String tagAdd(){
		return "/tagview/add";
	}
	
	@PostMapping("/tag/saveWord")
	public String saveWord(Topic topic,String words){
		
		this.topicService.save(topic);
		
		String[] oneWord = words.split("\n|\r\n|\r");
		for (int i = 0; i < oneWord.length; i++) {
			Word word = new Word();
			String[] newWord = oneWord[i].split(",");
			word.setWord(newWord[0]);
			word.setWeight(Float.parseFloat(newWord[1]));
			word.setFrequency(Integer.parseInt(newWord[2]));
			word.setTfidf(Float.parseFloat(newWord[3]));
			word.setTopic(topic);
			wordService.save(word);
		}
		return "redirect:/tag-setting";
		
	}
}
