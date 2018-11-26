package edu.gzhu.its;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.system.entity.Resource;
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
	
	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void addAuthorization(){
		Resource resource = this.resourceService.findById(1);
		
		Resource resource1 = new Resource();

		resource1.setParent(resource);
		resource1.setMenu(true);
		resource1.setRemark("角色管理");
		resource1.setName("角色管理");
		resource1.setUrl("/role/list");
		
		Resource resource2 = new Resource();

		resource2.setParent(resource);
		resource2.setMenu(true);
		resource2.setRemark("菜单管理");
		resource2.setName("菜单管理");
		resource2.setUrl("/resource/list");
		
		Resource resource3 = new Resource();

		resource3.setParent(resource);
		resource3.setMenu(true);
		resource3.setRemark("菜单树");
		resource3.setName("菜单树");
		resource3.setUrl("/resource/tree");
		
		this.resourceService.save(resource1);
		this.resourceService.save(resource2);
		this.resourceService.save(resource3);
		Set<Resource> resources =new HashSet<Resource>();
		resources.add(resource1);
		Role role = this.roleService.findById(1l);
		role.setId(1l);
		
		role.setResources(resources);
		this.roleService.update(role);
		
		
	}

}
