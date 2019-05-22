package edu.gzhu.its.teacher;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.profile.entity.Course;
import edu.gzhu.its.profile.service.ICourseService;
import edu.gzhu.its.system.entity.User;

/**
 * 教师功能控制器
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherContoller {
	
	@Resource
	private HttpSession httpSession;
	
	@Resource
	private ICourseService courseService;
	
	/**
	 * 教师教授的课程列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		//取得当前用户
		User user = (User) this.httpSession.getAttribute("currentUser");
		
		pageIndex = (pageIndex==null)?1:pageIndex;
		
		pageSize = (pageSize==null)?15:pageSize;
		//取得教师的课程列表
		PageData<Course> pageData =this.courseService.getPageData(pageIndex, pageSize, " and teacher_id= "+user.getId());
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pageIndex", pageIndex);
		
		return "teacher/list";
	}

}
