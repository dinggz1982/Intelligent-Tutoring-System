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

	public List<Integer> userIds = Arrays.asList(127, 128, 129, 130, 131, 132, 133, 134, 135, 136);

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
	
	public static void main(String[] args) {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 100; i++) {
				System.out.println(i);
				if(i==10)break;
			}
		}
		
	}
}
