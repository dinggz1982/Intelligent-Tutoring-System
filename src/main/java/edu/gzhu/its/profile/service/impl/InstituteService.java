package edu.gzhu.its.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.Institute;
import edu.gzhu.its.profile.service.IInstituteService;

@Service("instituteService")
public class InstituteService extends BaseDAOImpl<Institute, Integer> implements IInstituteService{

	private final static Logger logger = LoggerFactory.getLogger(InstituteService.class);

}
