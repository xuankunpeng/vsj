package com.jiangyifen.ec2.service.eaoservice;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jiangyifen.ec2.entity.Department;

public interface DeptService {

	@Transactional
	public void saveDept(Department dept);

	@Transactional
	public List<Department> getAllDepts();

	@Transactional
	public Department getDept(Long deptId);

	@Transactional
	public void updateDept(Department dept);

	@Transactional
	public void deleteDept(Department dept);

}
