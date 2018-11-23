package edu.gzhu.its.corpus.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gzhu.its.base.model.PageData;
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
@RequestMapping("/corpus")
public class UserCommentController {
	public List<Integer> userIds = Arrays.asList(127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 138, 140, 141);

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

		// 用户的标注总数
		// int totalCount = this.userTaskService.getCountBySql("select count(*)
		// from
		// user_task where user_id="+currentUser.getId());

		// 已标注的总数
		int hasRemarkCount = this.userTaskService.getCountBySql(
				"select count(*) from user_task where user_id=" + currentUser.getId() + " and isAnnotationed=1 ");

		// 未标注
		int notRemarkCount = this.userTaskService.getCountBySql(
				"select count(*) from user_task where user_id=" + currentUser.getId() + " and isAnnotationed=0 ");

		UserTask task = this.userTaskService
				.getByHql(" user_id=" + currentUser.getId() + " and isAnnotationed=0 order by user_comment_id asc ");
		if (task != null) {
			UserComment comment = task.getUserComment();
			model.addAttribute("notRemarkCount", notRemarkCount);
			model.addAttribute("hasRemarkCount", hasRemarkCount);
			model.addAttribute("comment", comment);
			model.addAttribute("taskId", task.getId());
		}
		return "/userComment/myJob";
	}

	/**
	 * 我的标注
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/myRemark")
	public String myRemark(Integer pageIndex, Integer pageSize, Model model) {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		Map<String, Object> map = new HashMap<>();
		User currentUser = (User) session.getAttribute("currentUser");
		map.put("user_id", currentUser.getId());
		PageData<UserRemark> pageData = this.userRemarkService.getPageData(pageIndex, pageSize, map);
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "userComment/myRemark";
	}

	@GetMapping("/saveUserComment")
	public String saveUserComment(String invalidComment, Long commentId, Long taskId, String[] contentRelated,
			String[] emotionRelated, String[] otherRelated) {
		User currentUser = (User) session.getAttribute("currentUser");
		UserComment comment = new UserComment();
		comment.setId(commentId);
		UserRemark remark = new UserRemark();
		remark.setCreateTime(new Date());
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

		this.userRemarkService.saveUserRemark(remark, taskId);

		return "redirect:/myJob?message=标注成功";
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
	public String progress(Model model) {
		// 取得标注的用户
		List<User> users = this.userService.find(" where id >=127");
		List<UserRemarkBean> remarks = new ArrayList<>();
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			UserRemarkBean bean = new UserRemarkBean();
			int hasRemarkCount = this.userTaskService.getCountBySql(
					"select count(*) from user_task where user_id=" + user.getId() + " and isAnnotationed=1 ");
			int notRemarkCount = this.userTaskService.getCountBySql(
					"select count(*) from user_task where user_id=" + user.getId() + " and isAnnotationed=0 ");
			bean.setHasRemarkCount(hasRemarkCount);
			bean.setNotRemarkCount(notRemarkCount);
			bean.setUser(user);
			remarks.add(bean);
		}

		int hasRemarkCount = this.userCommentService
				.getCountBySql("select count(*) from user_comment where isAnnotationed=1");
		int notRemarkCount = this.userCommentService
				.getCountBySql("select count(*) from user_comment where isAnnotationed=0");
		model.addAttribute("hasRemarkCount", hasRemarkCount);
		model.addAttribute("notRemarkCount", notRemarkCount);
		model.addAttribute("remarks", remarks);
		return "/userComment/progress";
	}

	/**
	 * 申请标注页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/apply")
	public String apply(Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		model.addAttribute("currentUser", currentUser);
		return "/userComment/apply";
	}
	
	/**
	 * 保存用户的标注申请
	 * @return
	 */
	@GetMapping("/apply")
	public String saveApply(int number){
		User currentUser = (User) session.getAttribute("currentUser");
		this.userTaskService.updateUserTasks(currentUser.getId(), number);
		return "redirect:/myJob?message=申请成功";
	}
	
	
	@GetMapping("/create")
	public String create(Model model) throws SQLException {
		List<UserComment> comments = this.userCommentService.findAll();
		List<UserTask> tasks = new ArrayList<UserTask>();
		int i = 0;
		int n = 0;
		for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
			if (i < 200) {
				UserComment userComment = (UserComment) iterator.next();
				long user1Id = userIds.get(n);

				int userTaskCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="
						+ user1Id + " and user_comment_id=" + userComment.getId());
				if (userTaskCount == 0) {
					User user = new User();
					user.setId(user1Id);
					UserTask task = new UserTask();
					task.setAnnotationed(false);
					task.setUser(user);
					task.setUserComment(userComment);
					tasks.add(task);
					i++;
				} else {
					if (n == 9) {
						User user = new User();
						Random random = new Random();
						int k = random.nextInt(userIds.size());

						long userId = userIds.get(k);
						user.setId(userId);
						UserTask task = new UserTask();
						task.setAnnotationed(false);
						task.setUser(user);
						task.setUserComment(userComment);
						tasks.add(task);
						i++;
					} else {
						User user = new User();
						user.setId(userIds.get(n + 1));
						UserTask task = new UserTask();
						task.setAnnotationed(false);
						task.setUser(user);
						task.setUserComment(userComment);
						tasks.add(task);
						i++;
					}
				}
				if (n == 9) {
					n = 0;
				}
				n++;
			} else {
				this.userTaskService.saveUserTasks(tasks);
				tasks.clear();
				i = 0;
			}
		}
		return null;
	}

}
