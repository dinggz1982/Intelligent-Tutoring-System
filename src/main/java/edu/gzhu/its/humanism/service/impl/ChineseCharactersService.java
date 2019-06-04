package edu.gzhu.its.humanism.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.humanism.entity.ChineseCharacters;
import edu.gzhu.its.humanism.entity.Spell;
import edu.gzhu.its.humanism.service.IChineseCharactersService;
import edu.gzhu.its.study.service.impl.RollCallInfoService;

@Service("chineseCharactersService")
public class ChineseCharactersService extends BaseDAOImpl<ChineseCharacters, Long> implements IChineseCharactersService{
	private final static Logger logger = LoggerFactory.getLogger(RollCallInfoService.class);

	@Override
	public List<Spell> getSpellByHanziId(int hanziId) {
		// TODO Auto-generated method stub
		return null;
	}
}