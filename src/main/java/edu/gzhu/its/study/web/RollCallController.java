package edu.gzhu.its.study.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;

import edu.gzhu.its.system.service.IUserService;

/**
 * 点名
 * <p>Title : RollCallController</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午2:41:16
 */
@Controller
public class RollCallController {
	
	@Resource
	private IUserService userService;

	/**
	 * 到点名页面
	 * <p>方法名:rollCall </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年3月6日 下午2:42:43
	 * @return
	 */
	@GetMapping("/rollCall")
	public String rollCall(Model model){
		//取得当前课程对应的学生，目前先简化处理
		/*List<User> users = this.userService.findBySql("classInfo.id", 1);
		model.addAttribute("users", users);*/
		
		List<Object[]> users = this.userService.findByNaviteSql("select id,username,xuehao,img from sys_user where class_id=1");
		List<Integer> ids = new ArrayList<Integer>();
		List<String> usernames = new ArrayList<String>();
		List<String> imgs = new ArrayList<String>();
		List<String> xuehaos = new ArrayList<String>();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ids.add(Integer.parseInt(objects[0].toString()));
			usernames.add(objects[1].toString());
			xuehaos.add(objects[2].toString());
			imgs.add(objects[3].toString());
		}
		
		
		Gson g = new Gson();
		String json1 = g.toJson(ids);
		model.addAttribute("ids", json1);
		
		String json2 = g.toJson(usernames);
		model.addAttribute("usernames", json2);
		
		String json3 = g.toJson(xuehaos);
		model.addAttribute("xuehaos", json3);
		
		String json4 = g.toJson(imgs);
		model.addAttribute("imgs", json4);
		
		return "study/rollCall";
	}
	
}
