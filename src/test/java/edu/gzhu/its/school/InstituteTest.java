package edu.gzhu.its.school;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.profile.entity.Institute;
import edu.gzhu.its.profile.entity.School;
import edu.gzhu.its.profile.service.IInstituteService;
import edu.gzhu.its.profile.service.ISchoolService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstituteTest {
	@Resource
	private ISchoolService schoolService;
	
	@Resource
	private IInstituteService instituteService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void addSchool(){
		School school = this.schoolService.findById(1);
		String[] name = {"教育学院","计算机学院","土木工程学院","机电学院","地理学院","法学院","外文学院","食品学院"};
		for (int i = 0; i < name.length; i++) {
			String string = name[i];
			Institute institute = new Institute();
			institute.setName(string);
			institute.setSchool(school);
			this.instituteService.save(institute);
		}
	}
	

}
