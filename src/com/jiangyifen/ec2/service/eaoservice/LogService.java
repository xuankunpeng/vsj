package com.jiangyifen.ec2.service.eaoservice;

import org.springframework.transaction.annotation.Transactional;

import com.jiangyifen.ec2.entity.Log;

public interface LogService {

	@Transactional
	public void saveLog(Log log);
	
	@Transactional
	public Log findLog(long id);
}
