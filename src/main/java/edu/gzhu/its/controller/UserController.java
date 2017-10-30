package edu.gzhu.its.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gzhu.its.entity.User;
import edu.gzhu.its.service.IUserService;

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
