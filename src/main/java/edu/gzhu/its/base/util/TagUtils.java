package edu.gzhu.its.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.gzhu.its.experiment.entity.Topic;
import edu.gzhu.its.experiment.entity.Word;

/**
 * 处理excel标签信息
 * @author Administrator
 *
 */
public class TagUtils {

	public static List<Word> getTags(String fileName,Topic topic){
		List<Word> words = new ArrayList<Word>();
		 try {
			 if(fileName.indexOf(".xlsx")!=-1) {
				//1、获取文件输入流
				InputStream inputStream = new FileInputStream(fileName);
				//2、获取Excel工作簿对象
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream); 
				//3、得到Excel工作表对象
				XSSFSheet sheetAt = workbook.getSheetAt(0);
				 //4、循环读取表格数据
				 for (Row row : sheetAt) {
					 //首行（即表头）不读取
			            if (row.getRowNum() == 0) {
			                continue;
			            }
			            //读取当前行中单元格数据，索引从0开始
			            String wordStr = row.getCell(0).getStringCellValue();
			            row.getCell(1).setCellType(CellType.STRING);
			            String weight = row.getCell(1).getStringCellValue();
			            row.getCell(2).setCellType(CellType.STRING);
			            String frequency = row.getCell(2).getStringCellValue();
			            row.getCell(3).setCellType(CellType.STRING);
			            String tfidf = row.getCell(3).getStringCellValue();

			            Word word = new Word();
			            word.setWord(wordStr);
			            word.setFrequency(Integer.parseInt(frequency));
			            word.setTfidf(Float.parseFloat(tfidf));
			            word.setWeight(Float.parseFloat(weight));
			            word.setTopic(topic);
			            words.add(word);
				 }
				 //5、关闭流
			        workbook.close();
			    }
			 
			 else  {
				//1、获取文件输入流
					InputStream inputStream = new FileInputStream(fileName);
					//2、获取Excel工作簿对象
					 HSSFWorkbook workbook = new HSSFWorkbook(inputStream); 
					//3、得到Excel工作表对象
					 HSSFSheet sheetAt = workbook.getSheetAt(0);
					 //4、循环读取表格数据
					 for (Row row : sheetAt) {
						 //首行（即表头）不读取
				            if (row.getRowNum() == 0) {
				                continue;
				            }
				            //读取当前行中单元格数据，索引从0开始
				            String wordStr = row.getCell(0).getStringCellValue();
				            row.getCell(1).setCellType(CellType.STRING);
				            String weight = row.getCell(1).getStringCellValue();
				            row.getCell(2).setCellType(CellType.STRING);
				            String frequency = row.getCell(2).getStringCellValue();
				            row.getCell(3).setCellType(CellType.STRING);
				            String tfidf = row.getCell(3).getStringCellValue();

				            Word word = new Word();
				            word.setWord(wordStr);
				            word.setFrequency(Integer.parseInt(frequency));
				            word.setTfidf(Float.parseFloat(tfidf));
				            word.setWeight(Float.parseFloat(weight));
				            word.setTopic(topic);
				            words.add(word);
					 }
					 //5、关闭流
				        workbook.close();
				    }
		 }catch (IOException e) {
			        e.printStackTrace();
			    }
		return  words;
	}
}
