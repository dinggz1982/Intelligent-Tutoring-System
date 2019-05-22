package edu.gzhu.its.school;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.profile.entity.Course;
import edu.gzhu.its.profile.service.ICourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
	@Resource
	private ICourseService courseService;
	
	@Test
	public void addCourse() {
		Course course = new Course();
		course.setName("网络教育软件设计与开发2019");
		//course.set
		this.courseService.save(course);
		
	}

}
