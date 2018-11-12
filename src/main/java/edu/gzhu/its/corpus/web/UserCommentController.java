package edu.gzhu.its.corpus.web;

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
import edu.gzhu.its.corpus.service.IUserCommentService;
import edu.gzhu.its.corpus.service.IUserRemarkService;
import edu.gzhu.its.system.entity.User;

@Controller
public class UserCommentController {

	@Resource
	private IUserCommentService userCommentService;

	@Resource
	private IUserRemarkService userRemarkService;

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
		// 取得用户已标注的评论
		List<Object[]> objects = this.userCommentService
				.findByNaviteSql("select userComment_id from user_remark where user_id=" + currentUser.getId());

		String hql = "";

		if (objects != null && objects.size() > 0) {
			String ids = "";
			for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
				Object objects2 = (Object) iterator.next();
				if (objects2 != null) {
					ids = ids + objects2 + ",";
				}
			}
			ids = ids.substring(0, ids.length() - 1);
			hql = " isAnnotationed=0  and id NOT in (" + ids + ")  order by id asc";
		} else {
			hql = " isAnnotationed=0 order by id asc ";
		}
		
		UserComment comment = this.userCommentService.getByHql(hql);
		model.addAttribute("comment", comment);
		return "/userComment/myJob";
	}

	@GetMapping("/saveUserComment")
	public String saveUserComment(String invalidComment, Long commentId, String[] contentRelated,
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
		this.userRemarkService.saveUserRemark(remark);

		return "/userComment/myJob";
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

}
