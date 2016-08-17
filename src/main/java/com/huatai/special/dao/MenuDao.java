package com.huatai.special.dao;

import java.util.List;

import com.huatai.special.common.MyBatisRepository;
import com.huatai.special.model.Menu;
import com.huatai.special.model.MyPage;

@MyBatisRepository
public interface MenuDao {
	/**
	 * 添加菜单
	 * @param role
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:16:38
	 */
	public long insertMenu(Menu menu);
	
	/**
	 * 修改菜单
	 * @param role
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:18:37
	 */
	public int updateMenuById(Menu menu);
	
	
	public int deleteMenuById(Long id);
	
	public Menu selectMenuById(Long id);
	
	public List<Menu> selectMenuLimit(MyPage<Menu> page);
	/**
	 * 查询当前用户菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> selectMenuByUserInfoId(Long userId);
	/**
	 * 根据父菜单id查询菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> selectMenuByParentId(Long parentId);
	
	public List<Menu> selectAllMenuIsDeleteFlag();
	
	public List<Menu> findAllMenuByRole(Long roleId);
	/**
	 * 查找所有父菜单
	 * @return
	 */
	public List<Menu> findParentMenu();
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<Menu> selectAllMenu();

	public List<Menu> getMyMenuByParentId(Long parentId);

	public List<Menu> selectSellerMenuByUserInfoId(Menu menu);
}
