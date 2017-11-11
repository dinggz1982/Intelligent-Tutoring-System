package edu.gzhu.its.system.service.impl;

import org.springframework.stereotype.Service;

import edu.gzhu.its.base.impl.BaseDAOImpl;
import edu.gzhu.its.service.IUserService;
import edu.gzhu.its.system.entity.User;

@Service("userService")
public class UserService extends BaseDAOImpl<User, Long> implements IUserService{

	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return this.findOneBySql("username", username);
	}


}
