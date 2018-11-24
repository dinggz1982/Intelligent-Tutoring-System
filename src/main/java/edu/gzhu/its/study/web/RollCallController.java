package edu.gzhu.its.study.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.study.entity.RollCall;
import edu.gzhu.its.study.entity.RollCallInfo;
import edu.gzhu.its.study.service.IRollCallInfoService;
import edu.gzhu.its.study.service.IRollCallService;
import edu.gzhu.its.system.entity.ResourceButton;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

/**
 * 点名
 * <p>Title : RollCallController</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午2:41:16
 */
@Controller
public class RollCallController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private IRollCallService rollCallService;
	
	@Resource
	private IRollCallInfoService rollCallInfoService;

	/**
	 * 到点名页面
	 * <p>方法名:rollCall </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年3月6日 下午2:42:43
	 * @return
	 */
	@GetMapping("/rollCall")
	public String rollCall(Model model,String classInfo){
		//取得当前课程对应的学生，目前先简化处理
		/*List<User> users = this.userService.findBySql("classInfo.id", 1);
		model.addAttribute("users", users);*/
		if(classInfo==null){
			classInfo="1";
		}
		List<Object[]> users = this.userService.findByNaviteSql("select id,username,xuehao,img from sys_user where class_id="+classInfo);
		List<Integer> ids = new ArrayList<Integer>();
		List<String> usernames = new ArrayList<String>();
		List<String> imgs = new ArrayList<String>();
		List<String> xuehaos = new ArrayList<String>();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ids.add(Integer.parseInt(objects[0].toString()));
			usernames.add(objects[1].toString());
			xuehaos.add(objects[2].toString());
			imgs.add(objects[3].toString());
		}
		
		
		Gson g = new Gson();
		String json1 = g.toJson(ids);
		model.addAttribute("ids", json1);
		
		String json2 = g.toJson(usernames);
		model.addAttribute("usernames", json2);
		
		String json3 = g.toJson(xuehaos);
		model.addAttribute("xuehaos", json3);
		
		String json4 = g.toJson(imgs);
		model.addAttribute("imgs", json4);
		
		return "study/rollCall";
	}
	
	@GetMapping("/saveRollCall")
	public String saveRollCall(Model model){
		
		String[] id = request.getParameterValues("id");
		String[] xuehao = request.getParameterValues("xuehao");
		String[] username = request.getParameterValues("username");

		List<User> users = new ArrayList<User>();
		for (int i = 0; i < xuehao.length; i++) {
			User user = new User();
			user.setId(Long.parseLong(id[i].toString()));
			user.setUsername(username[i]);
			user.setXuehao(xuehao[i]);
			users.add(user);
		}
		model.addAttribute("users", users);
		List<Object[]> allStudents = this.userService.findByNaviteSql("select id,username from sys_user where class_id=1");
		model.addAttribute("allStudents", allStudents);

		return "study/submitRollCall";
	}
	
	/**
	 * 提交点名信息
	 * <p>方法名:submitRollCall </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年3月7日 上午11:47:09
	 * @param model
	 * @return
	 */
	@GetMapping("/submitRollCall")
	public String submitRollCall(Model model){
		
		String name = request.getParameter("name");
		
		String[] userIds= request.getParameterValues("id");

		String[] types = 	request.getParameterValues("type");
		rollCallService.saveRollCall(name, userIds, types);
		
		return "study/submitRollCallSuccess";
	}
	
	
	@GetMapping("/rollCallList")
	public String rollCallList(Integer pageIndex,Integer pageSize,Model model){
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<RollCall> pageData = this.rollCallService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "study/list";
	}
	
	/**
	 * 查看点名信息
	 * <p>方法名:showRollCall </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年3月21日 上午1:52:16
	 * @return
	 */
	@GetMapping("/showRollCall/{id}")
	public String showRollCall(@PathVariable int id,Model model){
		RollCall call = this.rollCallService.findById(id);
		List<RollCallInfo> callInfos = this.rollCallInfoService.findBySql("rollCall.id", id);
		model.addAttribute("callInfos", callInfos);
		model.addAttribute("call", call);
		return "study/showRollCall";
	}
	
	
	
}
