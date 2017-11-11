package edu.gzhu.its.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	/**
	 * 处理登录
	 * @return
	 */
	@PostMapping("doLogin")
	public String doLogin(){
		
		
		return "";
	}

}
