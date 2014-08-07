package com.jiangyifen.ec2.eao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.jiangyifen.ec2.eao.UserEao;
import com.jiangyifen.ec2.entity.User;

public class UserEaoImpl extends BaseEaoImpl implements UserEao {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -9210303861864738413L;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {

		String jpql = "select s from User as s where 1=1 ";

		List<User> list = super.getEntityManager().createQuery(jpql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers(String str) {

		String jpql = "select s from User as s where 1=1 and s.username like ?1 ";
		Query query = super.getEntityManager().createQuery(jpql);
		query.setParameter(1, "%" + str + "%");

		List<User> list = query.getResultList();

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> getAllUsers(Map map) {
		String userName = (String) map.get("userName");
		String id = (String) map.get("id");

		String jpql = "select s from User as s where 1=1 ";
		if (userName != null && !userName.equals("")) {
			jpql += " and s.username like ?1 ";
		}
		if (id != null && !id.equals("")) {
			jpql += " and s.id=?2 ";
		}

		Query query = super.getEntityManager().createQuery(jpql);
		if (userName != null && !userName.equals("")) {
			query.setParameter(1, "%" + userName + "%");
		}
		if (id != null && !id.equals("")) {

			query.setParameter(2, Long.valueOf(id));
		}

		List<User> list = query.getResultList();

		return list;
	}

}
