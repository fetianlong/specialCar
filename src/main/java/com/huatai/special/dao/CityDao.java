package com.huatai.special.dao;

import java.util.List;

import com.huatai.special.common.MyBatisRepository;
import com.huatai.special.model.City;

@MyBatisRepository
public interface CityDao {
    int deleteByPrimaryKey(Long id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Long id);
    
    List<City> selectAllCity();

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}