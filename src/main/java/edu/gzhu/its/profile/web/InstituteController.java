package edu.gzhu.its.profile.web;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.profile.entity.Institute;
import edu.gzhu.its.profile.entity.School;
import edu.gzhu.its.profile.service.IInstituteService;
import edu.gzhu.its.profile.service.ISchoolService;

@Controller
@RequestMapping("/institute")
public class InstituteController {
	
	@Autowired
	private IInstituteService instituteService;
	@Autowired
	private ISchoolService schoolService;
	
	//url=域名:端口/intitute/list
	/**
	 * 学院list
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		pageIndex = (pageIndex==null)?1:pageIndex;
		pageSize = (pageSize==null)?15:pageSize;
		PageData<Institute> pageData = this.instituteService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList",pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pageIndex", pageIndex);
	return  "institute/list";	
	}

	
	/**
	 * 新增学院
	 * @return
	 */
	@GetMapping("/add")
	public String add(Model model){
		try {
			List<School> schools = 	this.schoolService.findAll();
			model.addAttribute("schools",schools);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "institute/add";
	}
}
