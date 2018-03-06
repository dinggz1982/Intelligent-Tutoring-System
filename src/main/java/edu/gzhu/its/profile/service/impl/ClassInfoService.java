package edu.gzhu.its.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.profile.entity.ClassInfo;
import edu.gzhu.its.profile.service.IClassInfoService;
/**
 * 班级信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("classInfoService")
public class ClassInfoService extends BaseDAOImpl<ClassInfo, Integer> implements IClassInfoService{

	private final static Logger logger = LoggerFactory.getLogger(ClassInfoService.class);



}
