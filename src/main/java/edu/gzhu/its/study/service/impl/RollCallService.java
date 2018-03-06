package edu.gzhu.its.study.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.study.entity.RollCall;
import edu.gzhu.its.study.service.IRollCallService;
/**
 * 点名信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("rollCallService")
public class RollCallService extends BaseDAOImpl<RollCall, Integer> implements IRollCallService{

	private final static Logger logger = LoggerFactory.getLogger(RollCallService.class);



}
