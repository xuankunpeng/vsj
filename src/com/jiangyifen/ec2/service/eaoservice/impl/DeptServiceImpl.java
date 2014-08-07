package com.jiangyifen.ec2.service.eaoservice.impl;

import java.util.List;

import com.jiangyifen.ec2.eao.DeptEao;
import com.jiangyifen.ec2.entity.Department;
import com.jiangyifen.ec2.service.eaoservice.DeptService;

public class DeptServiceImpl implements DeptService {

	private DeptEao deptEao;

	@Override
	public void saveDept(Department dept) {

		deptEao.save(dept);

	}

	public DeptEao getDeptEao() {
		return deptEao;
	}

	public void setDeptEao(DeptEao deptEao) {
		this.deptEao = deptEao;
	}

	@Override
	public List<Department> getAllDepts() {
		
		return deptEao.getAllDepts();
	}

	@Override
	public Department getDept(Long deptId) {

		return deptEao.get(Department.class, deptId);
	}

	@Override
	public void updateDept(Department dept) {

		deptEao.update(dept);
	}

	@Override
	public void deleteDept(Department dept) {

		deptEao.delete(dept);
	}

}
