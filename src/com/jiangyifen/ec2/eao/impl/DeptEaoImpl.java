package com.jiangyifen.ec2.eao.impl;

import java.util.List;

import com.jiangyifen.ec2.eao.DeptEao;
import com.jiangyifen.ec2.entity.Department;

public class DeptEaoImpl extends BaseEaoImpl implements DeptEao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAllDepts() {

		String jpql = "select s from Department as s where 1=1 ";

		List<Department> list = super.getEntityManager().createQuery(jpql).getResultList();

		return list;
	}

}
