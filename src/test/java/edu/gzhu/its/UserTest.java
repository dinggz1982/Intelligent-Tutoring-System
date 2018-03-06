package edu.gzhu.its;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IRoleService;
import edu.gzhu.its.system.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement  
public class UserTest {

	@Resource
	private IUserService userService;

	@Resource
	private IRoleService roleService;

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
		role2.setName("学生");
		role2.setDescription("这是学生角色");

		this.roleService.save(role1);
		this.roleService.save(role2);

		User user = new User();
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
		user.setPassword(bc.encode("dgz123"));
		user.setUsername("丁国柱");
		user.setEmail("dgz888@163.com");
		user.setNickName("丁国柱");
		user.setSex("男");
		Set<Role>  roles = new HashSet<Role>();
		roles.add(role1);
		//roles.add(role2);
		user.setRoles(roles);
		
		userService.saveUser(user);
	}
	
	@Test
	public void getPageDataTest(){
		PageData<User> data = this.userService.getPageData(1, 10, null);
		System.out.println(data.getTotalCount());
	}
	
	@Test
	@Rollback(false) 
	public void addUser() throws Exception{
		for (int i = 0; i < 100; i++) {
			User user = new User();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
			user.setPassword(bc.encode("123456"));
			user.setUsername("丁国柱");
			user.setEmail("dgz888@163.com");
			user.setNickName("丁国柱");
			user.setSex("男");
			List<Role>  roles = new ArrayList<Role>();
			userService.saveUser(user);
		}
	}

	
	@Test
	@Rollback(value=false)
	public void addRoleByUser() throws Exception{
		User user = this.userService.findById(2l);
		List<Role> roles = this.roleService.findAll();
		 Set<Role> set = new HashSet<Role>(roles);
		user.setRoles(set);
		this.userService.update(user);
	}
}
