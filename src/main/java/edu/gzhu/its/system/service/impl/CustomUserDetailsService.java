package edu.gzhu.its.system.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IResourceService;
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
	@Resource
	private IResourceService resourceService;

	/**
	 * 用户权限获取
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 本例使用User中的name作为用户名:
		User  user = userService.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + username + " not found");
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		/*//Collection<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			grantedAuths.add(new SimpleGrantedAuthority(role.getName()));
		}*/
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),grantedAuths);
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		Set<edu.gzhu.its.system.entity.Resource> resources = this.resourceService.getResourcesByUserId(user);
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (edu.gzhu.its.system.entity.Resource res : resources) {
			// 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
		//	authSet.add(new SimpleGrantedAuthority(res.getUrl()));
		}
		return authSet;
	}
}
