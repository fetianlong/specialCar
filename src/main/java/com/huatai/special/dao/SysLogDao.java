package com.huatai.special.dao;

import com.huatai.special.common.MyBatisRepository;
import com.huatai.special.model.SysLog;

@MyBatisRepository
public interface SysLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}