package com.jiangyifen.ec2.eao;

import java.util.List;
import java.util.Map;

import com.jiangyifen.ec2.entity.User;

public interface UserEao extends BaseEao {
	
	public List<User> getAllUsers();
	
	public List<User> getAllUsers(String str);
	
	@SuppressWarnings("rawtypes")
	public List<User> getAllUsers(Map map);
	
}
