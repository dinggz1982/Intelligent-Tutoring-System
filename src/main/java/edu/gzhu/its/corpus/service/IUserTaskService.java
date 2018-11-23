package edu.gzhu.its.corpus.service;

import java.util.List;

import edu.gzhu.its.base.service.BaseService;
import edu.gzhu.its.corpus.entity.UserTask;

public interface IUserTaskService extends BaseService<UserTask, Long>{
	
	public void saveUserTasks(List<UserTask> tasks);
	/**
	 * 更新用户的标注
	 * @param userId
	 */
	public void updateUserTasks(long userId,int number);
	

}
