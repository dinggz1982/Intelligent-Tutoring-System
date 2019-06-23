package edu.gzhu.its.corpus.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.corpus.entity.ProblemRemark;
import edu.gzhu.its.corpus.service.IProblemRemarkService;

@Service("problemRemarkService")
@Transactional
public class ProblemRemarkService extends BaseDAOImpl<ProblemRemark, Long> implements IProblemRemarkService {
	private final static Logger logger = LoggerFactory.getLogger(ProblemRemarkService.class);

	@Override
	public void batchSave(List<ProblemRemark> entitys) {
		// TODO Auto-generated method stub
		
	}

}
