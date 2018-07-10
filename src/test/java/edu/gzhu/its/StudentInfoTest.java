package edu.gzhu.its;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

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
}
