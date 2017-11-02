package edu.gzhu.its.its;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItsApplicationTests {
	
	@Resource
	private IUserService userService;

	@Test
	public void contextLoads() {
		System.out.println(this.userService.findById(1l).getEmail());
		
	}

}
