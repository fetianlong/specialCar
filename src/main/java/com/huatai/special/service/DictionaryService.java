package com.huatai.special.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huatai.special.dao.DictionaryDao;
import com.huatai.special.model.Dictionary;
import com.huatai.special.model.MyPage;

@Component
@Transactional
public class DictionaryService {
	@Autowired
	private DictionaryDao dDictionaryDao;

	public Long add(Dictionary model) {
		return dDictionaryDao.add(model);
	}

	public Integer update(Dictionary model) {
		return dDictionaryDao.update(model);
	}

	public Dictionary findById(Long id) {
		return dDictionaryDao.findById(id);

	}

	public Integer deleteById(Long id) {
		return dDictionaryDao.deleteById(id);

	}

	public int findCountByDictionary(Dictionary model) {
		return dDictionaryDao.findCountByDictionary(model);
	}

	public List<Dictionary> selectDictionaryLimit(MyPage<Dictionary> page) {
		return dDictionaryDao.selectDictionaryLimit(page);
	}

	/**
	 * 根据CODE获取数据
	 * @param code
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月25日 下午3:34:31
	 */
	public Dictionary getDictionaryByCode(String code) {
		// TODO Auto-generated method stub
		return dDictionaryDao.getDictionaryByCode(code);
	}
	
	public List<Dictionary> getListDictionary(String parentCode) {
		Dictionary dic = new Dictionary();
		dic.setParentCode(parentCode);
		return dDictionaryDao.getListDictionary(dic);
	}
	
	public Dictionary getDictionaryById(Long id) {
		return dDictionaryDao.getDictionaryById(id);
	}
	
	public List<Dictionary> getParentDictionary() {
		return dDictionaryDao.getParentDictionary();
	} 

}