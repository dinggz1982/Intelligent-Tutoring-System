package edu.gzhu.its.qa.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.jena.atlas.json.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;

import edu.gzhu.its.qa.model.Mine;

@Controller
public class QaController {

	@GetMapping("/qa")
	public String qa(){
		
		return "qa/index";
	}
	
	/**
	 * @return
	 */
	@GetMapping("/qa/init")
	@ResponseBody
	public Map inti(){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("code", 0);
			JSONObject mine = new JSONObject();
			
			mine.put("username",  "丁国柱");
			mine.put("id",  "123");
			mine.put("status",  "online");
			mine.put("sign",  "123");
			mine.put("avatar",  "1.jpg");
			JSONObject data = new JSONObject();
			data.put("mine", mine);
			

		   /* //好友列表
		    ,"friend": [{
		      "groupname": "前端码屌" //好友分组名
		      ,"id": 1 //分组ID
		      ,"list": [{ //分组下的好友列表
		        "username": "贤心" //好友昵称
		        ,"id": "100001" //好友ID
		        ,"avatar": "a.jpg" //好友头像
		        ,"sign": "这些都是测试数据，实际使用请严格按照该格式返回" //好友签名
		        ,"status": "online" //若值为offline代表离线，online或者不填为在线
		      }, …… ]
		    }, …… ]
		    */
			//朋友接口
			JSONObject friendgroup1 = new JSONObject();
			friendgroup1.put("groupname",  "中华传统文化问答机器人");
			friendgroup1.put("id",  "1");
			
			JSONArray array = new JSONArray();
			JSONObject hanzi = new JSONObject();
			hanzi.put("username", "汉字大师");
			hanzi.put("id", "1000");
			hanzi.put("avatar", "2.jpg");
			hanzi.put("sign", "3000个汉字你随便问");
			hanzi.put("status", "online");
			
			JSONObject poetry = new JSONObject();
			poetry.put("username", "古诗词大师");
			poetry.put("id", "1001");
			poetry.put("avatar", "2.jpg");
			poetry.put("sign", "唐诗宋词你随便问");
			poetry.put("status", "online");
			array.add(hanzi);
			array.add(poetry);
			friendgroup1.put("list", array);
			
			JSONArray friendgroups = new JSONArray();
			friendgroups.add(friendgroup1);
			data.put("friend", friendgroups);
			map.put("data",data);

		}catch(Exception e){
			map.put("code", 1);
			map.put("msg", "存在异常！");
		}
		return map;
	}
	
	
	@GetMapping("/qa/question")
	@ResponseBody
	public Map<String,Object> question(String sendId,String content,String toId){
		System.out.println(sendId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("answer", content +"回答");
		map.put("timestamp", new java.sql.Timestamp(new Date().getTime()));
		return map;
	}
	
	@GetMapping(value = "/qa/friend")
	@ResponseBody
	public String friend() {

		String xiaoding = "{ 'status': 1, 'msg': 'ok', 'data': [ { 'name': '小丁问答', 'nums': 1, 'id': 1, 'item': [ { 'id': '100001', 'name': '汉字大师', 'face': 'http://lcell.bnu.edu.cn//images/header/default.jpg' }  ] } ] }";
		return xiaoding.replaceAll("'", "\"");
	}

	@GetMapping(value = "/qa/group")
	@ResponseBody
	public String group() {

		String group = "{ 'status': 1, 'msg': 'ok', 'data': [ { 'id': '100001', 'name': '小丁', 'time': '10:23', 'face': 'http://lcell.bnu.edu.cn//images/header/default.jpg' }, { 'id': '100002', 'name': '丁国柱', 'time': '昨天', 'face': 'http://lcell.bnu.edu.cn//images/header/default.jpg' }  ] }";
		return group.replaceAll("'", "\"");
	}

	@GetMapping(value = "/qa/chatlog")
	@ResponseBody
	public String chatlog() {

		String group = "{ 'status': 1, 'msg': 'ok', 'data': [ { 'id': '100001', 'name': '小丁', 'time': '10:23', 'face': 'http://lcell.bnu.edu.cn//images/header/default.jpg'}  ] }";
		return group.replaceAll("'", "\"");
	}

	@GetMapping(value = "/qa/groups")
	@ResponseBody
	public String groups() {

		String groups = "{ 'status': 1, 'msg': 'ok', 'data': [ { 'id': '100001', 'name': '小丁', 'face': 'http://lcell.bnu.edu.cn/images/header/default.jpg' } } ] }";
		return groups.replaceAll("'", "\"");
	}

}
