package edu.gzhu.its.emotion.service.impl;

import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.emotion.entity.Emotion;
import edu.gzhu.its.emotion.service.IEmotionService;

@Service("emotionService")
public class EmotionService extends BaseDAOImpl<Emotion, Integer> implements IEmotionService {
	
}
