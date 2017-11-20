package edu.gzhu.its.system.service.impl;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

@Service("userService")
public class UserService extends BaseDAOImpl<User, Long> implements IUserService{
	
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User findByName(String username) {
		return this.findOneBySql("username", username);
	}

	@Override
	public boolean saveUser(User user) throws Exception {
		LinkedHashMap<String,Object> map = new LinkedHashMap<>();
		map.put("username", user.getUsername());
		Object count  = this.findCount(map);
		if(count!=null&&!count.toString().equals("0")){
			logger.error("用户名：" +user.getUsername()+"已存在");
			throw new Exception("用户名：" +user.getUsername()+"已存在!" );
		}
		else{
			this.save(user);
		}
		return false;
	}


}
