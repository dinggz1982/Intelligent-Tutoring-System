package edu.gzhu.its.system.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.gzhu.its.system.entity.Resource;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IResourceService;
import edu.gzhu.its.system.service.IUserService;
@Service
public class CtUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceService resourceService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 本例使用User中的name作为用户名:
		User user = userService.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + username + " not found");
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		/*
		 * //Collection<GrantedAuthority> grantedAuths = new
		 * HashSet<GrantedAuthority>(); Set<Role> roles = user.getRoles(); for
		 * (Role role : roles) { grantedAuths.add(new
		 * SimpleGrantedAuthority(role.getName())); }
		 */
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuths);

	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		Set<Resource> resources = this.resourceService.getResourcesByUserId(user);
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resource res : resources) {
			authSet.add(new SimpleGrantedAuthority(res.getUrl()));
		}
		return authSet;
	}

}
