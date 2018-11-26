package edu.gzhu.its;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.profile.entity.ClassInfo;
import edu.gzhu.its.profile.entity.Subject;
import edu.gzhu.its.profile.service.IClassInfoService;
import edu.gzhu.its.profile.service.ISubjectService;
import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

/**
 * 学生
 * <p>Title : StudentInfo</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:20:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentInfoTest {
	
	@Resource
	private ISubjectService subjectService;
	
	@Resource
	private IClassInfoService classInfoService;
	
	@Resource
	private IUserService userService;
	
	
	/**
	 * 
	 * <p>方法名:AddSubject </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年3月6日 下午1:21:13
	 */
	@Test
	public void addSubject(){
		Subject subject = new Subject();
		subject.setCode("gzu_jyxy_jyjs");
		subject.setName("教育技术学");
		this.subjectService.save(subject);
	}
	
	
	public void addClass(){
		Subject subject = new Subject();
		subject.setId(1);
		ClassInfo classInfo = new ClassInfo();
		classInfo.setGrade("2016");
		classInfo.setName("教育技术学161班");
		classInfo.setSubject(subject);
		this.classInfoService.save(classInfo);
	}

	@Test
	@Transactional
	@Rollback(false) 
	public void saveYjs() throws Exception{
		Role role = new Role();
 		role.setId(2l);
 		ClassInfo classInfo = new ClassInfo();
 		classInfo.setId(4);
 		String[] names = {"史湘衣"};
 		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			User user = new User();
    		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
    		user.setPassword(bc.encode("123456"));
    		user.setUsername(name);
    		user.setNickName(name);
    		user.setXuehao("20180116"+i);
    		/*if(i==0){
    			user.setSex("男");
    			user.setImg("/static/its/images/boy.png");
    		}else{*/
    			user.setSex("女");
    			user.setImg("/static/its/images/girl.png");
    		//}
    		
    		user.setEthnicity("汉族");
    		Set<Role>  roles = new HashSet<Role>();
    		roles.add(role);
    		user.setRoles(roles);
    		user.setClassInfo(classInfo);
    		userService.saveUser(user);
		}
		
	}
	
	
	@Test
	@Transactional
	@Rollback(false) 
	public void save161(){
		 /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:/161班级名单.txt")),
                                                                         "UTF-8"));
            String lineTxt = null;
            Role role = new Role();
    		role.setId(2l);
    		ClassInfo classInfo = new ClassInfo();
    		classInfo.setId(1);
            while ((lineTxt = br.readLine()) != null) {
                String[] subs = lineTxt.split("	");
               /* for (int i = 0; i < subs.length; i++) {
					System.out.print(subs[i] + ",");
					
				}*/
                User user = new User();
        		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
        		user.setPassword(bc.encode("123456"));
        		user.setUsername(subs[4]);
        		user.setNickName(subs[4]);
        		user.setXuehao(subs[3]);
        		user.setSex(subs[5]);
        		user.setEthnicity(subs[6]);
        		Set<Role>  roles = new HashSet<Role>();
        		roles.add(role);
        		user.setRoles(roles);
        		user.setClassInfo(classInfo);
        		userService.saveUser(user);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

	}
	
	@Test
	public void getStudent(){
		//excel文件路径文件1539104367548.xls   文件1539104355010.xls
        String excelPath = "C:/Users/Administrator/Downloads/文件1539104355010.xls";
        Role role = new Role();
		role.setId(2l);
		ClassInfo classInfo = new ClassInfo();
		classInfo.setId(3);
        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        //学号
                        String xuehao =row.getCell(0).toString();
                        //System.out.print(xuehao);
                        
                        String name =row.getCell(1).toString();
                       //System.out.print(name);
                        
                        String sex =row.getCell(2).toString();
                        System.out.println(xuehao+"   "+name+"   "+sex);
                        
                        User user = new User();
                		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
                		user.setPassword(bc.encode("123456"));
                		user.setUsername(name);
                		user.setNickName(name);
                		user.setXuehao(xuehao);
                		user.setSex(sex);
                		Set<Role>  roles = new HashSet<Role>();
                		roles.add(role);
                		user.setRoles(roles);
                		user.setClassInfo(classInfo);
                		userService.saveUser(user);

                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
