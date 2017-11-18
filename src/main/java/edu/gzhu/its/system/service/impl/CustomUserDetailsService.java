package edu.gzhu.its.system.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;
/**
 * spring security 用户验证类
 * <p>Title : CustomUserDetailsService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2017年11月12日 上午1:57:35
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	 	@Resource  
	    private IUserService userService;  
	  
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  
	        //本例使用User中的name作为用户名:  
	        User user = userService.findByName(username);
	        if (user == null) {  
	            throw new UsernameNotFoundException("UserName " + username + " not found");  
	        }  
	        return  user;  
	    }  
}
