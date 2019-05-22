package edu.gzhu.its.profile.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.profile.entity.School;
import edu.gzhu.its.profile.service.ISchoolService;

/**
 * 学校控制器
 * @author dingguozhu
 * @Controller
 *
 */
@Controller
public class SchoolController {
	
	@Autowired
	private ISchoolService schoolService;
	
	/**
	 * 学校list
	 * @return
	 */
	@GetMapping("/school/list")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		
		if(pageIndex==null){
			pageIndex=1;
		}
		if(pageSize==null){
			pageSize=15;
		}
		PageData<School> pageData =  this.schoolService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pageIndex", pageIndex);
		
		return "school/list";
	}
	
	/**
	 * 新增学校
	 * @return
	 */
	@GetMapping("/school/add")
	public String add(){
		return "school/add";
	}
	
	/**
	 * 保存学校
	 * @return
	 */
	@PostMapping("/school/save")
	public String save(School school){
		System.out.println("====");
		this.schoolService.save(school);
		
		return "school/saveSuccess";
		
	}

}
