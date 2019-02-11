package edu.gzhu.its.homework.web;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;
/**
 * 作业管理系统
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/homeWork")
public class HomeWorkController {

	@Autowired
	private HttpSession session;
	@Autowired
	private IUserService userService;
	
	@GetMapping("/home")
	public String home(Model model){
		//获取用户总数
		int userCount = this.userService.getUserCount();
		model.addAttribute("userCount", userCount);
		
		return "homeWork/home";
	}
	
	@GetMapping({"","/","/index"})
	public String index(Model model){
		//判断用户角色
		User currentUser=  (User) session.getAttribute("currentUser");
		Set<Role> roles = currentUser.getRoles();
		String myRole="" ;
		
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			myRole = role.getName()+","+myRole;
		}
		if(myRole.indexOf("管理员")!=-1){
			myRole="管理员";
		}else if(myRole.indexOf("教师")!=-1){
			myRole="教师";
		}else {
			myRole="学生";
		}
		model.addAttribute("myRole", myRole);
		return "homeWork/index";
	}
	
	/**
	 * 新增作业
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public String add(Model model){
		//判断用户角色
		User currentUser=  (User) session.getAttribute("currentUser");
		Set<Role> roles = currentUser.getRoles();
		String myRole="" ;
		
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			myRole = role.getName()+","+myRole;
		}
		if(myRole.indexOf("管理员")!=-1){
			myRole="管理员";
		}else if(myRole.indexOf("教师")!=-1){
			myRole="教师";
		}else {
			myRole="学生";
		}
		model.addAttribute("myRole", myRole);
		return "homeWork/index";
	}
}
