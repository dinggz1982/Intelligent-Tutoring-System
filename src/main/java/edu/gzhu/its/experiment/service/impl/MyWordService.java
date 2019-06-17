package edu.gzhu.its.experiment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.experiment.entity.MyWord;
import edu.gzhu.its.experiment.service.IMyWordService;
/**
 * @author dinggz
 */
@Service("myWordService")
@Transactional
public class MyWordService extends BaseDAOImpl<MyWord, Integer> implements IMyWordService {
	private final static Logger logger = LoggerFactory.getLogger(MyWordService.class);
	
}
