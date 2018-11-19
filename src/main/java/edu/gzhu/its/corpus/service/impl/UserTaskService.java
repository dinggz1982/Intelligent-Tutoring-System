package edu.gzhu.its.corpus.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.corpus.entity.UserTask;
import edu.gzhu.its.corpus.service.IUserTaskService;
/**
 * 用户标注任务
 * @author dinggz
 */
@Service("userTaskService")
@Transactional
public class UserTaskService extends BaseDAOImpl<UserTask, Long> implements IUserTaskService {
	private final static Logger logger = LoggerFactory.getLogger(UserTaskService.class);

	@Override
	@Transactional
	public void saveUserTasks(List<UserTask> tasks) {
		for (Iterator iterator = tasks.iterator(); iterator.hasNext();) {
			UserTask userTask = (UserTask) iterator.next();
			this.save(userTask);
		}
		
	}
}
