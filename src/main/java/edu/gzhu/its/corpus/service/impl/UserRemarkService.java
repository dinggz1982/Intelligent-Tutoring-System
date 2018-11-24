package edu.gzhu.its.corpus.service.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.corpus.entity.ProblemRemark;
import edu.gzhu.its.corpus.entity.UserRemark;
import edu.gzhu.its.corpus.entity.UserTask;
import edu.gzhu.its.corpus.service.IProblemRemarkService;
import edu.gzhu.its.corpus.service.IUserRemarkService;
import edu.gzhu.its.corpus.service.IUserTaskService;
import edu.gzhu.its.system.entity.User;

/**
 * 用户标注评论
 * 
 * @author dinggz
 */
@Service("userRemarkService")
@Transactional
public class UserRemarkService extends BaseDAOImpl<UserRemark, Long> implements IUserRemarkService {
	private final static Logger logger = LoggerFactory.getLogger(UserRemarkService.class);

	@Resource
	private IUserTaskService userTaskService;
	@Resource
	private IProblemRemarkService problemRemarkService;

	public List<Integer> userIds = Arrays.asList(127, 128, 129, 130, 131, 132, 133, 134, 135, 136);

	@Override
	@Transactional
	public void saveUserRemark(UserRemark remark, long taskId) {
		// 更新用户任务--设置为完成
		this.executeSql("update user_task set isAnnotationed=1 where id=" + taskId);
		String contentRelated = "";
		String emotionRelated = "";
		String otherRelated = "";
		boolean isEffective = false;
		if (remark.isEffectiveComment()) {

			if (remark.getContentRelated() != null && remark.getContentRelated().length() > 0) {
				if (remark.getContentRelated().indexOf("1") != -1) {
					contentRelated = contentRelated + "针对课程内容的提问" + ",";
				}
				if (remark.getContentRelated().indexOf("2") != -1) {
					contentRelated = contentRelated + "针对课程内容的评论与观点" + ",";
				}
				if (remark.getContentRelated().indexOf("3") != -1) {
					contentRelated = contentRelated + "课程内容的相关资源说明" + ",";
				}
			}
			if (remark.getEmotionRelated() != null && remark.getEmotionRelated().length() > 0) {
				if (remark.getEmotionRelated().indexOf("1") != -1) {
					emotionRelated = emotionRelated + "对课程内容的情感倾向" + ",";
				}
				if (remark.getEmotionRelated().indexOf("2") != -1) {
					contentRelated = contentRelated + "对教师或教学风格的情感倾向" + ",";
				}
			}
			if (remark.getOtherRelated() != null && remark.getOtherRelated().length() > 0) {
				if (remark.getOtherRelated().indexOf("1") != -1) {
					otherRelated = otherRelated + "对网络课程技术支持的问题或评价" + ",";
				}
				if (remark.getOtherRelated().indexOf("2") != -1) {
					otherRelated = otherRelated + "社交类评论 " + ",";
				}
				if (remark.getOtherRelated().indexOf("3") != -1) {
					otherRelated = otherRelated + "对学习状态或学习进度的描述" + ",";
				}
				if (remark.getOtherRelated().indexOf("4") != -1) {
					otherRelated = otherRelated + "其他不属于上述类别的评论" + ",";
				}
			}

		} else {
			isEffective = false;
		}

		// 查询本条用户评论是否已被其他用户标注
		List<UserRemark> remarks = this
				.find(" where userComment_id=" + remark.getUserComment().getId() + " and user_id <>" + remark.getUser().getId());
		if (remarks != null) {
			// 当前只有一个人标注
			if (remarks.size() == 1) {
				UserRemark oldRemark = remarks.get(0);
				// 和其他人标注的相同
				if (oldRemark.equals(remark)) {
					if (isEffective) {
						this.executeSql("update user_comment set isAnnotationed=1,contentRelated=" + contentRelated
								+ ",emotionRelated=" + emotionRelated + ",otherRelated=" + otherRelated + " where id="
								+ remark.getUserComment().getId());
					} else {
						this.executeSql("update user_comment set isAnnotationed=1,isEffective=0 where id="
								+ remark.getUserComment().getId());
					}
				} else {
					// 和其他人标注不同，交给第三人处理
					// 找到标注任务最少的人
					List<Object[]> objects = this.findBySql(
							"select t.uid,min(t.ucount) from (select user_id as uid,count(*) as ucount from user_task  group by user_id) t");
					if (objects != null && objects.size() > 0) {
						Object[] object = objects.get(0);
						long userId = Long.parseLong(object[0].toString());
						// 确保其他用户不是当前用户
						if (userId == oldRemark.getUser().getId() || userId == remark.getUser().getId()) {
							Random random = new Random();
							userIds.remove(oldRemark.getUser().getId());
							userIds.remove(remark.getUser().getId());
							int n = random.nextInt(userIds.size());
							userId = userIds.get(n);
						}
						User user = new User();
						user.setId(userId);
						UserTask task = new UserTask();
						task.setAnnotationed(false);
						task.setUser(user);
						task.setUserComment(remark.getUserComment());
						this.userTaskService.save(task);
					}
				}
			} else if (remarks.size() > 1) {
				boolean isFinished = false;
				for (Iterator iterator = remarks.iterator(); iterator.hasNext();) {
					UserRemark userRemark = (UserRemark) iterator.next();
					if (userRemark.equals(remark)) {
						if (isEffective) {
							this.executeSql("update user_comment set isAnnotationed=1,contentRelated=" + contentRelated
									+ ",emotionRelated=" + emotionRelated + ",otherRelated=" + otherRelated
									+ " where id=" + remark.getUserComment().getId());
						} else {
							this.executeSql("update user_comment set isAnnotationed=1,isEffective=0 where id="
									+ remark.getUserComment().getId());
						}
						isFinished = true;
						break;
					}
				}
				// 还未取得一致意见！~！！！！！
				if (!isFinished) {
					ProblemRemark problemRemark = new ProblemRemark();
					this.problemRemarkService.save(problemRemark);
				}
			}

		}

		this.save(remark);
	}

}
