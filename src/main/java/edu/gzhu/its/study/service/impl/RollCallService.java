package edu.gzhu.its.study.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.ClassInfo;
import edu.gzhu.its.study.entity.RollCall;
import edu.gzhu.its.study.entity.RollCallInfo;
import edu.gzhu.its.study.service.IRollCallInfoService;
import edu.gzhu.its.study.service.IRollCallService;
import edu.gzhu.its.system.entity.User;
/**
 * 点名信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("rollCallService")
public class RollCallService extends BaseDAOImpl<RollCall, Integer> implements IRollCallService{

	@Resource
	private IRollCallInfoService callInfoService;
	
	private final static Logger logger = LoggerFactory.getLogger(RollCallService.class);

	@Override
	@Transactional
	public void saveRollCall(String name, String[] userIds, String[] types) {
		RollCall call = new RollCall();
		call.setAddress("文逸楼254");
		ClassInfo classInfo = new ClassInfo();
		classInfo.setId(1);
		call.setClassInfo(classInfo);
		User teacher = new User();
		teacher.setId(1l);
		call.setTeacher(teacher);
		call.setName(name);
		call.setDate(new Date());
		this.save(call);
		
		for (int i = 0; i < userIds.length; i++) {
			RollCallInfo callInfo = new RollCallInfo();
			callInfo.setType(Integer.parseInt(types[i]));
			User user = new User();
			user.setId(Long.parseLong(userIds[i]));
			callInfo.setUser(user);
			callInfo.setRollCall(call);
			this.callInfoService.save(callInfo);
		}
	}



}
