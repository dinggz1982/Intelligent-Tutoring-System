package edu.gzhu.its.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserFrontController {
	
	@PostMapping("/admin")
	public String admin(){
		
		return "admin/index";
	}

}
