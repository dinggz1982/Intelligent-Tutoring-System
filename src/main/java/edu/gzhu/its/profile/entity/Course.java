package edu.gzhu.its.profile.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import edu.gzhu.its.system.entity.User;

/**
 * 课程
 * <p>Title : Course</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午2:26:12
 */
@Entity
@Table(name = "profile_course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	//课程名
	private String name;
	
	
	//对应专业
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	//上课教师
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;
	
	@Column(columnDefinition="varchar(255) comment '这门课的上课时间' ")
	private String studyTime;
	
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@OrderBy("id asc")
	private Set<ClassInfo> classInfos; 


	public int getId() {
		return id;
	}


	public String getStudyTime() {
		return studyTime;
	}


	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}


	public Set<ClassInfo> getClassInfos() {
		return classInfos;
	}


	public void setClassInfos(Set<ClassInfo> classInfos) {
		this.classInfos = classInfos;
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


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getTeacher() {
		return teacher;
	}


	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	
}
