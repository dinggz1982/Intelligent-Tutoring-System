package edu.gzhu.its.corpus.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.gzhu.its.system.entity.User;

/**
 * 用户标注
 * @author dinggz
 *
 */
@Entity
@Table(name = "user_remark")
public class UserRemark {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//标注的用户
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//private 

}
