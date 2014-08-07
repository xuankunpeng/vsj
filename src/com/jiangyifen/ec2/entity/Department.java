package com.jiangyifen.ec2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="vsj_dept")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department")
//	@SequenceGenerator(name = "department", sequenceName = "vsj_dept_id", allocationSize = 1)
	private Long id;
	
	@Column
	private Long deptNO;
	
	@Column
	private String deptName;
	
	@OneToMany(cascade =CascadeType.ALL,targetEntity = User.class,mappedBy="department")
	private List<User> users=new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptNO() {
		return deptNO;
	}

	public void setDeptNO(Long deptNO) {
		this.deptNO = deptNO;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
