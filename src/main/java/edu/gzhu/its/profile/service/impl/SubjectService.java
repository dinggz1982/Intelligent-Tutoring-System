package edu.gzhu.its.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.Subject;
import edu.gzhu.its.profile.service.ISubjectService;

@Service("subjectService")
public class SubjectService extends BaseDAOImpl<Subject, Integer> implements ISubjectService{

	private final static Logger logger = LoggerFactory.getLogger(SubjectService.class);



}
