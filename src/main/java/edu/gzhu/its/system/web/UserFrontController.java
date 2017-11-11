package edu.gzhu.its.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserFrontController {
	
	@GetMapping("/admin")
	public String admin(){
		
		return "admin/index";
	}

}
