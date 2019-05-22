package edu.gzhu.its.school;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.profile.entity.Institute;
import edu.gzhu.its.profile.entity.Major;
import edu.gzhu.its.profile.service.IMajorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorTest {
	@Resource
	private IMajorService majorService;
	
	@Test
	public void addMajor(){
		Major major  = new Major();
		Institute institute = new Institute();
		institute.setId(1);
		major.setInstitute(institute);
		major.setName("教育技术学");
		this.majorService.save(major);
		
	}

}
