package com.huatai.special.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huatai.special.dao.SysLogDao;
import com.huatai.special.model.SysLog;

@Component
@Transactional
public class SysLogService {
	
	@Autowired
	SysLogDao sysLogDao;
	
	public int insert(SysLog log) {
		return sysLogDao.insert(log);
	}
}
