package edu.gzhu.its.profile.web;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.profile.entity.ClassInfo;
import edu.gzhu.its.profile.service.IClassInfoService;
import edu.gzhu.its.system.entity.Resource;
import edu.gzhu.its.system.entity.ResourceButton;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IResourceButtonService;
import edu.gzhu.its.system.service.IResourceService;
import edu.gzhu.its.system.service.IUserService;

/**
 * 班级管理
 * @author dingguozhu
 *
 */
@Controller
public class ClassController {
	
	@Autowired
	private IClassInfoService classInfoService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceButtonService resourceButtonService;
	@Autowired
	private IResourceService resourceService;
	/**
	 * 列出班级信息
	 * @return
	 */
	@GetMapping("/class/list")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<ClassInfo> pageData = this.classInfoService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		
		/*//取得用户的按钮权限
		User currentUser=  (User) session.getAttribute("currentUser");
		
		System.out.println(request.getRequestURI());
		String url = request.getRequestURI();
		Resource resource = resourceService.getResourceByURL(url);
		Set<ResourceButton>  buttons = this.resourceButtonService.getResourceButtonByUserId(resource, currentUser);
		model.addAttribute("buttons", buttons);*/
		
		return "study/classList";
	}
	

}
