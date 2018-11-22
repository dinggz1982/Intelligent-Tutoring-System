package edu.gzhu.its;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.service.IResourceService;
import edu.gzhu.its.system.service.IRoleService;
import edu.gzhu.its.system.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement  
public class AuthorizationTest {
	
	@Resource
	private IResourceService resourceService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void addAuthorization(){
		edu.gzhu.its.system.entity.Resource resource = new edu.gzhu.its.system.entity.Resource();
		resource.setName("权限配置");
		resource.setMenu(true);
		resource.setRemark("权限");
		resource.setDelFlag(false);
		
		edu.gzhu.its.system.entity.Resource resource1 = new edu.gzhu.its.system.entity.Resource();

		resource1.setParent(resource);
		resource1.setMenu(true);
		resource1.setRemark("用户管理");
		resource1.setName("用户管理");
		resource1.setUrl("/user/list");
		this.resourceService.save(resource);
		this.resourceService.save(resource1);
		
		Set<edu.gzhu.its.system.entity.Resource> resources =new HashSet<edu.gzhu.its.system.entity.Resource>();
		resources.add(resource);
		resources.add(resource1);
		Role role = this.roleService.findById(1l);
		role.setId(1l);
		
		role.setResources(resources);
		this.roleService.update(role);
		
		
	}

}
