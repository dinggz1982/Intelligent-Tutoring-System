package edu.gzhu.its.corpus.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.corpus.entity.UserRemark;
import edu.gzhu.its.corpus.service.IUserRemarkService;
/**
 * 用户标注评论
 * @author dinggz
 */
@Service("userRemarkService")
@Transactional
public class UserRemarkService extends BaseDAOImpl<UserRemark, Long> implements IUserRemarkService {
	private final static Logger logger = LoggerFactory.getLogger(UserRemarkService.class);

	@Override
	@Transactional
	public void saveUserRemark(UserRemark remark) {
		this.save(remark);
		
	}

}
