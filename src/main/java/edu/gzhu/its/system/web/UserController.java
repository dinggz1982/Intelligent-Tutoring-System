package edu.gzhu.its.system.web;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.base.model.TableSplitResult;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

@Controller
public class UserController {
	
	@Resource
	private IUserService userService;
	
	@GetMapping("/user/userList")
	@ResponseBody
	public TableSplitResult<User> getUsers(Integer pageIndex,Integer pageSize){
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		
		PageData<User> pageData = this.userService.getPageData(pageIndex, pageSize, null);
		TableSplitResult<User> pageJson = new TableSplitResult<User>();
		pageJson.setTotal(pageData.getTotalCount());
		pageJson.setRows(pageData.getPageData());
		pageJson.setPage(pageIndex);
		return pageJson;
	}

}
