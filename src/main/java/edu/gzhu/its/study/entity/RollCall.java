package edu.gzhu.its.study.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.gzhu.its.profile.entity.ClassInfo;
import edu.gzhu.its.profile.entity.Course;
import edu.gzhu.its.system.entity.User;

/**
 * 点名
 * <p>Title : RollCall</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午2:30:33
 */
@Entity
@Table(name = "study_rollcall")
public class RollCall {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	//点名名字
	private String name;
	
	//点名对应的课程
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	//点名对应的教师
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User teacher;
	
	//点名对应的班级
	@ManyToOne
	@JoinColumn(name = "classInfo_id")
	private ClassInfo classInfo;

	//点名的时间
	private Date date;
	
	//点名的地点
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
