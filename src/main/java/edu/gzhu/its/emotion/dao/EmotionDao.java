package edu.gzhu.its.emotion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.gzhu.its.emotion.entity.Emotion;

public interface EmotionDao extends JpaRepository<Emotion, Integer>{

	@Query("select e from Emotion e where e.word=:word")
	public List<Emotion> getEmotionByWord(@Param(value = "word")  String word);

	@Query("select e from Emotion e ")
	public List<Emotion> findAll();
	
}
