package edu.gzhu.its;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.service.IUserService;
import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IRoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Resource
	private IUserService userService;

	@Resource
	private IRoleService roleService;

	@Test
	public void addAdminUser() {
		System.out.println(this.userService.findById(1l).getEmail());

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
		user.setNickName("dgz");
		user.setSex("男");
		List<Role>  roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);
		user.setRoles(roles);
		
		userService.save(user);
	}

}
