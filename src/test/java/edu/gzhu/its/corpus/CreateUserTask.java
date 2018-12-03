package edu.gzhu.its.corpus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.corpus.entity.UserComment;
import edu.gzhu.its.corpus.entity.UserTask;
import edu.gzhu.its.corpus.service.IUserCommentService;
import edu.gzhu.its.corpus.service.IUserTaskService;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

/**
 * 生成用户任务
 * 
 * @author dingguozhu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateUserTask {

	@Resource
	private IUserCommentService userCommentService;

	@Resource
	private IUserTaskService userTaskService;

	@Resource
	private IUserService userService;

	public List<Integer> userIds = Arrays.asList(127, 128, 129, 130, 131, 132, 133, 135, 136);

	public void create() throws SQLException {
		List<UserComment> comments = this.userCommentService.find(" where id>156250");
		List<UserTask> tasks = new ArrayList<UserTask>();
		int i = 0;
		for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
			if (i < 200) {
				UserComment userComment = (UserComment) iterator.next();
				Random random = new Random();
				int n = random.nextInt(userIds.size());

				long user1Id = userIds.get(n);
				// 查询当前用户的标注数是超限
				int user1TaskCount = this.userTaskService
						.getCountBySql("select count(*) from user_task where user_id=" + user1Id);
				if (user1TaskCount >= 20525) {
					userIds.remove(user1Id);
					n = random.nextInt(userIds.size());
					user1Id = userIds.get(n);
				}

				User user = new User();
				user.setId(user1Id);

				UserTask task = new UserTask();
				task.setAnnotationed(false);
				task.setUser(user);
				task.setUserComment(userComment);
				tasks.add(task);
				i++;
			} else {
				this.userTaskService.saveUserTasks(tasks);
				tasks.clear();
				i = 0;
			}
		}

	}

	@Test
	@Transactional
	@Rollback(false)
	public void createOther() throws SQLException {
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
	}

	@Test
	@Transactional
	@Rollback(false)
	public void setUser() {
		List<Object[]> es = this.userCommentService.findByNaviteSql(
				"select a.id,a.user_id from user_task a ,(select user_id,user_comment_id from user_task group by user_id,user_comment_id having count(1) > 1) as b where a.user_id=b.user_id and a.user_comment_id=b.user_comment_id");
		String ids = "(";
		for (Iterator iterator = es.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ids = ids + objects[0] + ",";
		}
		ids = ids.substring(0, ids.length() - 1) + ")";
		this.userCommentService.executeSql("update user_task set user_id=1 where id in " + ids);

	}

	@Test
	@Transactional
	@Rollback(false)
	public void add() throws SQLException {
		List<UserComment> comments = this.userCommentService.findAll();
		int n = 0;
		List<UserTask> tasks = new ArrayList<UserTask>();
		int i = 0;
		for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
			if (i < 200) {
				UserComment userComment = (UserComment) iterator.next();
				User user = new User();
				if (n == 8) {
					n = 0;
				}
				long user1Id = userIds.get(n);
				int userTaskCount = this.userTaskService.getCountBySql("select count(*) from user_task where user_id="
						+ user1Id + " and user_comment_id=" + userComment.getId());
				if (userTaskCount == 0) {
					n++;
					user.setId(user1Id);
					UserTask task = new UserTask();
					task.setAnnotationed(false);
					task.setUser(user);
					task.setUserComment(userComment);
					tasks.add(task);
					i++;
				} else {
					if (n == 8) {
						n = 0;
						user1Id = userIds.get(n++);

					} else {
						user1Id = userIds.get(n++ + 1);
					}
					user.setId(user1Id);
					UserTask task = new UserTask();
					task.setAnnotationed(false);
					task.setUser(user);
					task.setUserComment(userComment);
					tasks.add(task);
					i++;
				}
			} else {
				this.userTaskService.saveUserTasks(tasks);
				tasks.clear();
				i = 0;
			}
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void change(){
		userIds = Arrays.asList( 128, 129, 130, 131, 132, 133, 135);
		for (Iterator iterator = userIds.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			List<Object[]> list = this.userTaskService.findByNaviteSql("select id,user_id from user_task where user_id="+integer + " order by id desc limit 229");
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				Object[] objects = (Object[]) iterator2.next();
				this.userTaskService.executeSql("update user_task set user_id=136 where id="+ objects[0]);
			}
		}
		
	}

}
