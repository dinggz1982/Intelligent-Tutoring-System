package edu.gzhu.its.experiment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.experiment.entity.TagEditHistory;
import edu.gzhu.its.experiment.service.ITagEditHistoryService;
/**
 * @author dinggz
 */
@Service("tagEditHistoryService")
@Transactional
public class TagEditHistoryService extends BaseDAOImpl<TagEditHistory, Integer> implements ITagEditHistoryService {
	private final static Logger logger = LoggerFactory.getLogger(TagEditHistoryService.class);
	
}
