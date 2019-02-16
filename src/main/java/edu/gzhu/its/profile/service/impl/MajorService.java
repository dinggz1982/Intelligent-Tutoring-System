package edu.gzhu.its.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.Major;
import edu.gzhu.its.profile.service.IMajorService;

@Service("majorService")
public class MajorService extends BaseDAOImpl<Major, Integer> implements IMajorService{

	private final static Logger logger = LoggerFactory.getLogger(MajorService.class);



}
