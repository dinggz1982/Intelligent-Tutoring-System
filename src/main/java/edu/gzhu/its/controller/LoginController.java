package edu.gzhu.its.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	/**
	 * 系统登录页面
	 * @return
	 */
	@GetMapping("/login")
	public String longin(){
		return "login";
	}

}
