package edu.gzhu.its.emotion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.emotion.entity.Emotion;
import edu.gzhu.its.emotion.service.IEmotionService;

/**
 * Title: its Description: Copyright: Copyright (c) 2018 Company:广州大学-教育技术
 * Makedate:2018年11月27日 下午2:40:04
 * 
 * @author:丁国柱
 * @version 1.0
 * @since 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmotionTest {

	@Autowired
	private IEmotionService emotionService;

	@Test
	public void createWord() throws SQLException {
		List<Emotion> emotions = emotionService.findAll();
		String words ="";
		for (Iterator iterator = emotions.iterator(); iterator.hasNext();) {
			Emotion emotion = (Emotion) iterator.next();
			words =words+emotion.getWord()+"\t"+emotion.getCharacterType()+"\t"+"1000\n";
		}
		File file = new File("f:/userLibrary.dic");
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			os.write(words.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
