package edu.gzhu.its.school;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.profile.entity.School;
import edu.gzhu.its.profile.service.ISchoolService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolTest {
	@Resource
	private ISchoolService schoolService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void addSchool(){
		/*School school = new School("广州大学","广州番禺大学城");
		this.schoolService.save(school);
		School school1 = new School("中山大学","广州番禺大学城");
		this.schoolService.save(school1);
		*/
		String[] names = {"华南理工大学","广东外语外贸大学","华南师范大学","美术学院","星海音乐学院","广东药科大学"};
		for (int i = 0; i < names.length; i++) {
			String string = names[i];
			School school = new School(string,"广州番禺大学城");
			this.schoolService.save(school);
		}
		
		
		
	}
	

}
