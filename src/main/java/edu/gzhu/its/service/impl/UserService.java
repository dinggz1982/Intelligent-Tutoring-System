package edu.gzhu.its.service.impl;

import org.springframework.stereotype.Service;

import edu.gzhu.its.dao.impl.BaseDAOImpl;
import edu.gzhu.its.entity.User;
import edu.gzhu.its.service.IUserService;

@Service("userService")
public class UserService extends BaseDAOImpl<User, Long> implements IUserService{


}
