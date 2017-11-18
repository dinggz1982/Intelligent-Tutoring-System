package edu.gzhu.its.system.service;

import edu.gzhu.its.base.service.BaseService;
import edu.gzhu.its.system.entity.User;

public interface IUserService extends BaseService<User, Long>{
	/**
	 * <p>方法名:findByName </p>
	 * <p>Description : 根据用户名查询用户</p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月12日 上午1:59:20
	 * @param username
	 * @return
	 */
	public User findByName(String username);
	
	/**
	 * 保存用户
	 * <p>方法名:saveUser </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月18日 下午7:01:14
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user)  throws Exception;

}
