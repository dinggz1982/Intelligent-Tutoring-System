package edu.gzhu.its.corpus.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.gzhu.its.corpus.entity.UserComment;
import edu.gzhu.its.corpus.entity.UserRemark;
import edu.gzhu.its.corpus.entity.UserTask;
import edu.gzhu.its.corpus.model.UserRemarkBean;
import edu.gzhu.its.corpus.service.IUserCommentService;
import edu.gzhu.its.corpus.service.IUserRemarkService;
import edu.gzhu.its.corpus.service.IUserTaskService;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

@Controller
public class UserCommentController {

	@Resource
	private IUserCommentService userCommentService;

	@Resource
	private IUserRemarkService userRemarkService;
	@Resource
	private IUserTaskService userTaskService;
	
	@Resource
	private IUserService userService;

	@Autowired
	private HttpSession session;

	/**
	 * 我的工作
	 * 
	 * @return
	 */
	@GetMapping("/myJob")
	public String myJob(Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		
		//用户的标注总数
		//int totalCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="+currentUser.getId());
		
		//已标注的总数
		int hasRemarkCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="+currentUser.getId()+" and isAnnotationed=1 ");

		//未标注
		int notRemarkCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="+currentUser.getId()+" and isAnnotationed=0 ");

		
		
		UserTask task = this.userTaskService.getByHql(" user_id="+currentUser.getId()+" and isAnnotationed=0 order by id asc ");
		UserComment comment = task.getUserComment();
		model.addAttribute("notRemarkCount", notRemarkCount);
		model.addAttribute("hasRemarkCount", hasRemarkCount);
		model.addAttribute("comment", comment);
		model.addAttribute("taskId", task.getId());
		return "/userComment/myJob";
	}

	@GetMapping("/saveUserComment")
	public String saveUserComment(String invalidComment, Long commentId,Long taskId, String[] contentRelated,
			String[] emotionRelated, String[] otherRelated) {
		User currentUser = (User) session.getAttribute("currentUser");
		UserComment comment = new UserComment();
		comment.setId(commentId);
		UserRemark remark = new UserRemark();
		if (invalidComment != null && invalidComment.equals("1")) {

			remark.setUser(currentUser);
			remark.setEffectiveComment(false);
			remark.setUserComment(comment);
		} else {
			remark.setUser(currentUser);
			remark.setEffectiveComment(true);
			remark.setUserComment(comment);
			if (contentRelated != null && contentRelated.length > 0) {
				String content = "";
				for (int i = 0; i < contentRelated.length; i++) {
					content = content + contentRelated[i] + ",";
				}
				remark.setContentRelated(content);
			}
			if (emotionRelated != null && emotionRelated.length > 0) {
				String emotion = "";
				for (int i = 0; i < emotionRelated.length; i++) {
					emotion = emotion + emotionRelated[i] + ",";
				}
				remark.setEmotionRelated(emotion);
			}

			if (otherRelated != null && otherRelated.length > 0) {
				String other = "";
				for (int i = 0; i < otherRelated.length; i++) {
					other = other + otherRelated[i] + ",";
				}
				remark.setOtherRelated(other);
			}
		}
		
		this.userRemarkService.saveUserRemark(remark,taskId);

		return "redirect:/myJob";
	}

	/**
	 * 用户标注
	 * 
	 * @return
	 */
	@GetMapping("/userComment")
	public String userComment() {

		return "userComment";
	}

	/**
	 * 我的标注
	 * 
	 * @return
	 */
	@GetMapping("/myAnnotations")
	public String myAnnotations() {
		return "myAnnotations";
	}

	/**
	 * 其他人的标注
	 * 
	 * @return
	 */
	@GetMapping("/otherAnnotations")
	public String otherAnnotations() {
		return "otherAnnotations";
	}

	/**
	 * 标注进展
	 */
	@GetMapping("/progress")
	public String progress(Model model){
		//取得标注的用户
		List<User> users = this.userService.find(" where id between 127 and 136");
		List<UserRemarkBean> remarks = new ArrayList<>();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			UserRemarkBean bean = new UserRemarkBean();
			int hasRemarkCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="+user.getId()+" and isAnnotationed=1 ");
			int notRemarkCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="+user.getId()+" and isAnnotationed=0 ");
			bean.setHasRemarkCount(hasRemarkCount);
			bean.setNotRemarkCount(notRemarkCount);
			bean.setUser(user);
			remarks.add(bean);
		}
		
		model.addAttribute("remarks",remarks);
		return "/userComment/progress";
	}
	
}
