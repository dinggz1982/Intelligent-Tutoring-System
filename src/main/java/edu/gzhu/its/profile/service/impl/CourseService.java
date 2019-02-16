package edu.gzhu.its.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.Course;
import edu.gzhu.its.profile.service.ICourseService;
/**
 * 课程service
 * @author dingguozhu
 *
 */
@Service("courseService")
public class CourseService extends BaseDAOImpl<Course, Integer> implements ICourseService{

	private final static Logger logger = LoggerFactory.getLogger(CourseService.class);



}
