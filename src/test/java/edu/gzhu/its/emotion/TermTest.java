package edu.gzhu.its.emotion;

import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;

/**   
* Title: gzhu 
* Description:     
* Copyright: Copyright (c) 2018 
* Company:广州大学-教育技术 
* Makedate:2018年11月27日 下午2:13:13 
* @author:丁国柱
* @version 1.0
* @since 1.0 
**/
public class TermTest {

	
	public static void main(String[] args) {
		String str = "教育技术学是现代教育学发展的重要成果，教育技术参与教育过程，是对教育过程模式的优化提升，使得教育过程的组织序列更具逻辑，系统优化了分析和处理教育、教学问题的思路" ;
		
		System.out.println(BaseAnalysis.parse(str));
		System.out.println(ToAnalysis.parse(str));
		System.out.println(DicAnalysis.parse(str));
		System.out.println(IndexAnalysis.parse(str));
		System.out.println(NlpAnalysis.parse(str));
	}
	
	 @Test
	 public void wordTest(){
	 
	        Forest forest = null;
	        try {
	            forest=Library.makeForest(TermTest.class.getResourceAsStream("/library/userLibrary.dic"));//加载字典文件
	            String str = "糟报自恶言阿斯达岁的啊as的撒旦撒大苏打撒旦撒打算";
	            Result result=NlpAnalysis.parse(str,forest);//传入forest
	            List<Term> termList=result.getTerms();
	            for(Term term:termList){
	                System.out.println(term.getName()+":"+term.getNatureStr());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
