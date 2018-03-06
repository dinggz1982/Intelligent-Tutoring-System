package edu.gzhu.its.study.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.study.entity.RollCallInfo;
import edu.gzhu.its.study.service.IRollCallInfoService;
/**
 * 点名信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("rollCallInfoService")
public class RollCallInfoService extends BaseDAOImpl<RollCallInfo, Long> implements IRollCallInfoService{
	private final static Logger logger = LoggerFactory.getLogger(RollCallInfoService.class);

}
