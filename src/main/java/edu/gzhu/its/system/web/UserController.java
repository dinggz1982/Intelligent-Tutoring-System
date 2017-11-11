package edu.gzhu.its.system.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gzhu.its.service.IUserService;
import edu.gzhu.its.system.entity.User;

@Controller
public class UserController {
	
	@Resource
	private IUserService userService;
	
	@GetMapping("/user")
	@ResponseBody
	public String getUsers(){
		List<User> users = this.userService.findAll();
		
		return "userList";
	}
	
	

}
