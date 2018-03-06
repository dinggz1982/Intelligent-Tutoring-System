package edu.gzhu.its.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	/**
	 * 主页入口
	 * @return
	 */
	
	@RequestMapping(value={"","/","index"})
	public String index() {
		return "index";
	}
	
      
}
