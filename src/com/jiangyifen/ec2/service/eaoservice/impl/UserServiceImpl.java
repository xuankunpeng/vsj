package com.jiangyifen.ec2.service.eaoservice.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jiangyifen.ec2.eao.UserEao;
import com.jiangyifen.ec2.entity.User;
import com.jiangyifen.ec2.service.eaoservice.FlipSupportService;
import com.jiangyifen.ec2.service.eaoservice.UserService;

public class UserServiceImpl implements UserService, FlipSupportService<User>, Serializable {

	private static final long serialVersionUID = 5833779314383443801L;

	private UserEao userEao;

	public UserEao getUserEao() {
		return userEao;
	}

	public void setUserEao(UserEao userEao) {
		this.userEao = userEao;
	}

	@Override
	public void saveUser(User user) {
		userEao.save(user);
	}

	// @Override
	// public List<User> getAllUsers() {
	//
	// return userEao.getAllUsers();
	// }

	@Override
	public User getUser(Long userId) {

		return userEao.get(User.class, userId);
	}

	@Override
	public void updateUser(User user) {

		userEao.update(user);

	}

	@Override
	public void deleteUser(User user) {

		userEao.delete(user);
	}

	@Override
	public List<User> getAllUsers(String str) {

		return userEao.getAllUsers(str);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<User> getAllUsers(Map map) {

		return userEao.getAllUsers(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadPageEntities(int start, int length, String sql) {
		if ("".equals(sql.trim())) { // 代表不查询数据库
			return new ArrayList<User>();
		}
		return userEao.loadPageEntities(start, length, sql);
	}

	@Override
	public int getEntityCount(String sql) {

		return (int) userEao.getEntityCount(sql);
	}

}
