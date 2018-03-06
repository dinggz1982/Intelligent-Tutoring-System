package edu.gzhu.its.its;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IRoleService;
import edu.gzhu.its.system.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching //加入缓存
public class ItsApplicationTests {
	
	@Resource
	private IUserService userService;


	@Resource
	private IRoleService roleService;
	
	@Test
	public void contextLoads() {
		System.out.println(this.userService.findById(1l).getEmail());
	}
	


	@Test
	@Transactional  
	@Rollback(false) 
	public void addAdminUser() throws Exception {
	//	System.out.println(this.userService.findById(1l).getEmail());
		// 管理員角色
		Role role1 = new Role();
		role1.setName("管理员");
		role1.setDescription("这是管理员角色");

		Role role2 = new Role();
		role2.setName("普通用户");
		role2.setDescription("这是普通用户角色");

		this.roleService.save(role1);
		this.roleService.save(role2);

		User user = new User();
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
		user.setPassword(bc.encode("123456"));
		user.setUsername("admin");
		user.setEmail("dgz888@163.com");
		user.setNickName("admin");
		user.setSex("男");
		Set<Role>  roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		user.setRoles(roles);
		userService.saveUser(user);
	}
	

}
