package com.jiangyifen.ec2.eao;

import java.util.List;

import com.jiangyifen.ec2.entity.Department;

public interface DeptEao extends BaseEao {
	
	public List<Department> getAllDepts();
}
