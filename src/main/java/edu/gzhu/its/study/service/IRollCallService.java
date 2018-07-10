package edu.gzhu.its.study.service;

import edu.gzhu.its.base.service.BaseService;
import edu.gzhu.its.study.entity.RollCall;

public interface IRollCallService extends BaseService<RollCall, Integer>{
	
	public void saveRollCall(String name,String[] userIds,String[] types);

}
