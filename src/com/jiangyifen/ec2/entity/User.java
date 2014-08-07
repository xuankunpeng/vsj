package com.jiangyifen.ec2.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 用户
 * 
 * @author
 * 
 */
@Entity
@Table(name = "vsj_user")
public class User implements Serializable {

	private static final long serialVersionUID = -7492406779695710064L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
	@SequenceGenerator(name = "user", sequenceName = "vsj_user_id", allocationSize = 1)
	private Long id;

	@Column
	private int age;

	@Column
	private String dept = "";

	@Column
	private String phoneNumber = "";

	@Column
	private String username = "";

	@Column
	private String date;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = Department.class)
	@JoinColumn(name = "department_id", nullable = true, updatable = false)
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", dept=" + dept + ", phoneNumber=" + phoneNumber
				+ ", username=" + username + ", date=" + date + "]";
	}

}
