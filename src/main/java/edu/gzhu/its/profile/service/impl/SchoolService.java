package edu.gzhu.its.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.School;
import edu.gzhu.its.profile.service.ISchoolService;
/**
 * 学校信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("schoolService")
@Transactional
public class SchoolService extends BaseDAOImpl<School, Integer> implements ISchoolService{

	private final static Logger logger = LoggerFactory.getLogger(SchoolService.class);



}
