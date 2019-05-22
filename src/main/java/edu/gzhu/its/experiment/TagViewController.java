package edu.gzhu.its.experiment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TagViewController {
	
	@GetMapping("/tagView")
	public String tagView(){
		return "tagview/index";
	}

}
