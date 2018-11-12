package edu.gzhu.its.corpus.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.corpus.entity.UserComment;
import edu.gzhu.its.corpus.service.IUserCommentService;
/**
 * 用户评论
 * @author dinggz
 */
@Service("userCommentService")
@Transactional
public class UserCommentService extends BaseDAOImpl<UserComment, Long> implements IUserCommentService {
	private final static Logger logger = LoggerFactory.getLogger(UserCommentService.class);

}
