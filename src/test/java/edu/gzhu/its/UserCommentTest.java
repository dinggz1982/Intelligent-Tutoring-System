package edu.gzhu.its;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vdurmont.emoji.EmojiParser;

import edu.gzhu.its.corpus.entity.UserComment;
import edu.gzhu.its.corpus.service.IUserCommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCommentTest {

	@Resource
	private IUserCommentService userCommentService;

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(InputStream in, File file) throws IOException {
		Workbook wb = null;
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

	/**
	 * 判断文件是否是excel
	 * 
	 * @throws Exception
	 */
	public static void checkExcelVaild(File file) throws Exception {
		if (!file.exists()) {
			throw new Exception("文件不存在");
		}
		if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
			throw new Exception("文件不是Excel");
		}
	}

	@Test
	@Transactional
	public void update() throws Exception {
		String[] types = { "economic", "engineering", "history", "literature", "medicine", "science" };
		SimpleDateFormat   sdformat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < types.length; i++) {
			File file = new File("D:\\corpus\\" + types[i]);
			File[] files = file.listFiles();
			for (int j = 0; j < files.length; j++) {
				File excelFile = files[i];
				FileInputStream in = new FileInputStream(excelFile); // 文件流
				checkExcelVaild(excelFile);
				String name = excelFile.getName();
				Workbook workbook = getWorkbok(in, excelFile);
				 Sheet sheet = workbook.getSheetAt(0); 
				 int k=0;
				 for (Row row : sheet) {
					 if(k>=2){
						UserComment comment = new UserComment();
						String content = EmojiParser.removeAllEmojis(row.getCell(0).toString());
						
						comment.setContent(content);
						String createTime = row.getCell(1).toString();
						comment.setCreateTime(sdformat.parse(createTime));

						String against = row.getCell(2).toString();
						comment.setCourse(name.replace(".xls", ""));
						comment.setAgainst(Integer.parseInt(against.replace(".0", "")));
						
						String favCount = row.getCell(3).toString();
						comment.setFavCount(Integer.parseInt(favCount.replace(".0", "")));
						
						String vote = row.getCell(4).toString();
						comment.setVote(Integer.parseInt(vote.replace(".0", "")));
						comment.setCourseType(types[i]);
						this.userCommentService.save(comment);
						
						
					 }
					 k++;
					 
					 
				 }
			}

		}

	}

}
