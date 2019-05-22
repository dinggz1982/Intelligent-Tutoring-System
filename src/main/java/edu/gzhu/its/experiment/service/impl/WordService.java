package edu.gzhu.its.experiment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.experiment.entity.Word;
import edu.gzhu.its.experiment.service.IWordService;
/**
 * @author dinggz
 */
@Service("wordService")
@Transactional
public class WordService extends BaseDAOImpl<Word, Integer> implements IWordService {
	private final static Logger logger = LoggerFactory.getLogger(WordService.class);
	
}
