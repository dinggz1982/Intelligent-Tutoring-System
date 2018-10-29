package edu.gzhu.its.humanism.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 人文性知识图谱 入口
 * <p>Title : HumanismController</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年7月11日 下午4:24:00
 */
@Controller
@RequestMapping("/humanism")
public class HumanismController {

	/**
	 * 群体-人文性知识图谱入口
	 * <p>方法名:index </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年7月11日 下午4:25:10
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "humanism/index";
	}

}
