package edu.gzhu.its.emotion.service.impl;

import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.emotion.entity.UserCommentEmotionWord;
import edu.gzhu.its.emotion.service.IUserCommentEmotionWordService;

@Service("userCommentEmotionWordService")
public class UserCommentEmotionWordService extends BaseDAOImpl<UserCommentEmotionWord, Integer> implements IUserCommentEmotionWordService {
	
}
