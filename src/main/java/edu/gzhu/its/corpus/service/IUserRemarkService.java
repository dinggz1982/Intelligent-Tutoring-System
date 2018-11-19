package edu.gzhu.its.corpus.service;

import edu.gzhu.its.base.service.BaseService;
import edu.gzhu.its.corpus.entity.UserRemark;

public interface IUserRemarkService extends BaseService<UserRemark, Long>{
	
	public void saveUserRemark(UserRemark remark,long taskId);

}
