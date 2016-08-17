package com.huatai.special.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huatai.special.dao.CityDao;
import com.huatai.special.model.City;

@Component
@Transactional
public class CityService {
	
	@Autowired
	CityDao cityDao;
	
	public City selectByPrimaryKey(Long id){
		return cityDao.selectByPrimaryKey(id);
	}
    
    public List<City> selectAllCity(){
    	return cityDao.selectAllCity();
    };

}