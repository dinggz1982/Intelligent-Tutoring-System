package edu.gzhu.its.qa.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
			
			map.put("data",data);

		}catch(Exception e){
			map.put("code", 1);
			map.put("msg", "存在异常！");
		}
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
