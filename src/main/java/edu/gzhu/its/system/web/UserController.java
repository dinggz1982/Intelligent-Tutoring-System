package edu.gzhu.its.system.web;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.base.model.PageJson;
import edu.gzhu.its.service.IUserService;
import edu.gzhu.its.system.entity.User;

@Controller
public class UserController {
	
	@Resource
	private IUserService userService;
	
	@GetMapping("/user/userList")
	@ResponseBody
	public PageJson<User> getUsers(Integer pageIndex,Integer pageSize){
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		
		PageData<User> pageData = this.userService.getPageData(pageIndex, pageSize, null);
		PageJson<User> pageJson = new PageJson<User>();
		pageJson.setPage(pageData.getPageIndex());
		pageJson.setRecords(pageData.getTotalCount());
		pageJson.setTotal(pageData.getTotalPage());
		pageJson.setRows(pageData.getPageData());
		return pageJson;
	}

}
