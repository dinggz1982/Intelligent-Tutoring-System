package edu.gzhu.its.emotion;
/**   
* Title: its 
* Description:     
* Copyright: Copyright (c) 2018 
* Company:广州大学-教育技术 
* Makedate:2018年11月27日 下午3:02:05 
* @author:丁国柱
* @version 1.0
* @since 1.0 
**/

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.corpus.entity.UserComment;
import edu.gzhu.its.corpus.service.IUserCommentService;
import edu.gzhu.its.emotion.entity.Emotion;
import edu.gzhu.its.emotion.entity.UserCommentEmotionWord;
import edu.gzhu.its.emotion.service.IEmotionService;
import edu.gzhu.its.emotion.service.IUserCommentEmotionWordService;
import edu.gzhu.its.profile.service.impl.SubjectService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
	private final static Logger logger = LoggerFactory.getLogger(SubjectService.class);

	@Autowired
	private IUserCommentService userCommentService;
	@Autowired
	private IEmotionService emotionService;
	
	@Autowired
	private IUserCommentEmotionWordService userCommentEmotionWordService; 
	
	@Test
	@Transactional
	@Rollback(false)
	public void test() throws SQLException{
		Forest forest = null;
		List<UserComment> comments = this.userCommentService.findAll();
		for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
			UserComment userComment = (UserComment) iterator.next();
			
			try {
	            forest=Library.makeForest(TermTest.class.getResourceAsStream("/library/userLibrary.dic"));//加载字典文件
	            String str = userComment.getContent();
	            Result result=NlpAnalysis.parse(str,forest);//传入forest
	            List<Term> termList=result.getTerms();
	            for(Term term:termList){
	               // System.out.println(term.getName()+":"+term.getNatureStr());
	            	if(term.getName().length()>=2){
	                Emotion emotion = this.emotionService.getByHql(" word='" + term.getName()+"'");
	                if(emotion!=null) {
	                	UserCommentEmotionWord userCommentEmotionWord =this.userCommentEmotionWordService.getByHql(" word='" + term.getName()+"'");
	                	if(userCommentEmotionWord==null) {
	                		userCommentEmotionWord = new UserCommentEmotionWord();
	                		userCommentEmotionWord.setUserCommentId(userComment.getId()+"$$");
	                		userCommentEmotionWord.setWord(term.getName());
	                		userCommentEmotionWord.setWordCount(1);
		                	this.userCommentEmotionWordService.save(userCommentEmotionWord);

	                	}else {
	                		userCommentEmotionWord.setUserCommentId(userCommentEmotionWord.getUserCommentId()+userComment.getId()+"$$");
	                		userCommentEmotionWord.setWordCount(userCommentEmotionWord.getWordCount()+1);
	                		this.userCommentEmotionWordService.update(userCommentEmotionWord);	                	}
	                }
	            }
	            	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			
		}
		
		
		
		
		
		/* Forest forest = null;
	        try {
	        	
	        	UserComment comment = this.userCommentService.findById(25l);
	        	
	        	
	            forest=Library.makeForest(TermTest.class.getResourceAsStream("/library/userLibrary.dic"));//加载字典文件
	            String str = comment.getContent();
	            Result result=NlpAnalysis.parse(str,forest);//传入forest
	            List<Term> termList=result.getTerms();
	            for(Term term:termList){
	                System.out.println(term.getName()+":"+term.getNatureStr());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
		
		
		
	}
	

}
