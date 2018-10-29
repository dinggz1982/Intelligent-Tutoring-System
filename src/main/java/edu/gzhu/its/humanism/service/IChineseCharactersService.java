package edu.gzhu.its.humanism.service;

import java.util.List;


import edu.gzhu.its.base.service.BaseService;
import edu.gzhu.its.humanism.entity.ChineseCharacters;
import edu.gzhu.its.humanism.entity.Spell;

public interface IChineseCharactersService extends BaseService<ChineseCharacters, Integer> {
	public List<Spell> getSpellByHanziId(int hanziId);
}
