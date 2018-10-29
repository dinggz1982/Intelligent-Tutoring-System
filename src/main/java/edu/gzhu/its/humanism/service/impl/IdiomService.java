package edu.gzhu.its.humanism.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.humanism.entity.Idiom;
import edu.gzhu.its.humanism.service.IIdiomService;
import edu.gzhu.its.study.service.impl.RollCallInfoService;

/**
 * 成语服务
 * <p>Title : IdiomService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年7月11日 下午5:08:16
 */
@Service("idiomService")
public class IdiomService  extends BaseDAOImpl<Idiom, Long> implements IIdiomService{
	private final static Logger logger = LoggerFactory.getLogger(RollCallInfoService.class);


}
