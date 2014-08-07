package com.jiangyifen.ec2.service.eaoservice;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jiangyifen.ec2.entity.User;

public interface UserService extends FlipSupportService<User> {

	@Transactional
	public void saveUser(User user);

	// @Transactional
	// public List<User> getAllUsers();

	@Transactional
	public User getUser(Long userId);

	@Transactional
	public void updateUser(User user);

	@Transactional
	public void deleteUser(User user);

	@Transactional
	public List<User> getAllUsers(String str);
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public List<User> getAllUsers(Map map);
}
