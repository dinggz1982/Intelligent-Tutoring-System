package edu.gzhu.its.school;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.profile.entity.Major;
import edu.gzhu.its.profile.entity.Subject;
import edu.gzhu.its.profile.service.ISubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectTest {

	@Resource
	private ISubjectService subjectService;
	
	@Test
	public void addSubject(){
		Subject subject = new Subject();
		Major major = new Major();
		major.setId(1);
		subject.setMajor(major);
		subject.setName("网络教育软件设计与开发");
		this.subjectService.save(subject);
	}
}
