package edu.gzhu.its.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;

/**
 * 批量导入用户 处理excel标签信息
 * 
 * @author Administrator
 *
 */
public class UploadUserUtils {

	public static List<User> getUsers(String fileName,String defaultUrl) {
		List<User> users = new ArrayList<User>();
		try {
			if (fileName.indexOf(".xlsx") != -1) {
				// 1、获取文件输入流
				InputStream inputStream = new FileInputStream(fileName);
				// 2、获取Excel工作簿对象
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				// 3、得到Excel工作表对象
				XSSFSheet sheetAt = workbook.getSheetAt(0);
				// 4、循环读取表格数据
				for (Row row : sheetAt) {
					// 首行（即表头）不读取
					if (row.getRowNum() == 0) {
						continue;
					}
					// 读取当前行中单元格数据，索引从0开始
					if(row.getCell(1)!=null){
						row.getCell(1).setCellType(CellType.STRING);
						String username = row.getCell(1).getStringCellValue();
						User user = new User();
						user.setUsername(username);
						user.setXuehao(username);
						if(row.getCell(2)!=null){
							row.getCell(2).setCellType(CellType.STRING);
							String realName = row.getCell(2).getStringCellValue();
							user.setRealname(realName);
						}
						if(row.getCell(3)!=null){
							row.getCell(3).setCellType(CellType.STRING);
							String sex = row.getCell(3).getStringCellValue();
							user.setSex(sex);
						}
						if(row.getCell(4)!=null){
							row.getCell(4).setCellType(CellType.STRING);
							String email = row.getCell(4).getStringCellValue();
							user.setEmail(email);
						}
						BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
						user.setPassword(bc.encode("123456"));
						Role role = new Role();
						role.setId(2l);
						Set<Role> roles = new HashSet<Role>();
						roles.add(role);
						
						
						user.setUrl(defaultUrl);
						user.setRoles(roles);
						users.add(user);
					}
				}
				// 5、关闭流
				workbook.close();
			}

			else {
				// 1、获取文件输入流
				InputStream inputStream = new FileInputStream(fileName);
				// 2、获取Excel工作簿对象
				HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
				// 3、得到Excel工作表对象
				HSSFSheet sheetAt = workbook.getSheetAt(0);
				// 4、循环读取表格数据
				for (Row row : sheetAt) {
					// 首行（即表头）不读取
					if (row.getRowNum() == 0) {
						continue;
					}
					if(row.getCell(1)!=null){
						row.getCell(1).setCellType(CellType.STRING);
						String username = row.getCell(1).getStringCellValue();
						User user = new User();
						user.setUsername(username);
						user.setXuehao(username);
						if(row.getCell(2)!=null){
							row.getCell(2).setCellType(CellType.STRING);
							String realName = row.getCell(2).getStringCellValue();
							user.setRealname(realName);
						}
						if(row.getCell(3)!=null){
							row.getCell(3).setCellType(CellType.STRING);
							String sex = row.getCell(3).getStringCellValue();
							user.setSex(sex);
						}
						if(row.getCell(4)!=null){
							row.getCell(4).setCellType(CellType.STRING);
							String email = row.getCell(4).getStringCellValue();
							user.setEmail(email);
						}
						BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
						user.setPassword(bc.encode("123456"));
						Role role = new Role();
						role.setId(2l);
						Set<Role> roles = new HashSet<Role>();
						roles.add(role);
						user.setUrl(defaultUrl);
						user.setRoles(roles);
						users.add(user);
					}
				}
				// 5、关闭流
				workbook.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}
