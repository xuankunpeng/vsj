package com.jiangyifen.ec2.service.eaoservice.impl;

import com.jiangyifen.ec2.eao.LogEao;
import com.jiangyifen.ec2.entity.Log;
import com.jiangyifen.ec2.service.eaoservice.LogService;

public class LogServiceImpl implements LogService{

	private LogEao logEao;
	
	@Override
	public void saveLog(Log log) { 
		logEao.save(log);
	}

	@Override
	public Log findLog(long id) {
		 return logEao.get(Log.class, id);
	}

	public LogEao getLogEao() {
		return logEao;
	}

	public void setLogEao(LogEao logEao) {
		this.logEao = logEao;
	}

	
}
