package com.huatai.special.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huatai.special.dao.MenuDao;
import com.huatai.special.dao.RoleMenuDao;
import com.huatai.special.model.Menu;

@Component
@Transactional
public class MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	RoleMenuDao roleMenuDao;
	
	/**
	 * 添加菜单
	 * @param menu
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:16:38
	 */
	public long addMenu(Menu menu) {
		return menuDao.insertMenu(menu);
	}

	/**
	 * 修改权限
	 * @param role
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:18:37
	 */
	public int updateMenu(Menu menu) {
		return menuDao.updateMenuById(menu);
	}

	/**
	 * 删除菜单，并删除角色和菜单的关联关系
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年4月15日 上午10:33:58
	 */
	public int deleteMenuById(Long id) {
		roleMenuDao.deleteRoleMenuByMenuId(id);
		return menuDao.deleteMenuById(id);
	}

	/**
	 * 根据Id获取菜单信息
	 * @param roleId
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:21:12
	 */
	public Menu getMenuById(Long menuId) {
		return menuDao.selectMenuById(menuId);
	}
	
	/**
	 * 分页获取菜单数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	/*public List<Menu> selectMenuLimit(MyPage<Menu> page) {
		List<Menu> menuList = menuDao.selectMenuLimit(page);
		for(int i=0; i<menuList.size(); i++) {
			Menu menu = menuList.get(i);
			if(menu.getParentId() != null) {
				menuList.get(i).setName("&nbsp;&nbsp;&nbsp;&nbsp;"+ menu.getName());
			}
		}
		return menuList;
	}*/
	public List<Menu> selectAllMenu() {
		List<Menu> menus = menuDao.selectAllMenu();
		List<Menu> list = new ArrayList<Menu>();
		for (int i = 0; i < menus.size(); i++) {
			Menu menu = menus.get(i);
			if (menu.getParentId() == null) {
				menus.get(i).setName("<font style='font-weight:bold;font-style:normal;'>"+menu.getName()+"</font>");
				list.add(menu);
				for (int si = 0; si < menus.size(); si++) {
					Menu submenu = menus.get(si);
					if (menu.getId().equals(submenu.getParentId())) {
						menus.get(si).setName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ submenu.getName());
						list.add(submenu);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 查找当前用户的一级菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> selectMenuByUserInfoId(Long userId){
		return menuDao.selectMenuByUserInfoId(userId);
	}
	
	/**
	 * 根据父菜单查询子菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> selectMenuByParentId(Long parentId){
		return menuDao.selectMenuByParentId(parentId);
	}
	
	public List<Menu> selectAllMenuIsDeleteFlag(){
		return menuDao.selectAllMenuIsDeleteFlag();
	}
	
	public List<Menu> findAllMenuIsDeleteFlag() {
		return menuDao.selectAllMenuIsDeleteFlag();
	}
	
	public List<Menu> findAllMenuByRole(Long roleId) {
		return menuDao.findAllMenuByRole(roleId);
	}
	/**
	 * 找到所有父级菜单
	 * @return
	 */
	public List<Menu> findParentMenu(){
		return menuDao.findParentMenu();
	}
	
	/**
	 * 根据父级菜单ID获取旗下子菜单（包含当前父菜单）
	 * @param parentId 父菜单ID
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月19日 上午11:42:20
	 */
	public List<Menu> getMyMenuByParentId(Long parentId) {
		return menuDao.getMyMenuByParentId(parentId);
	}
	
	/**
	 * 根据当前一级菜单获取当前用户的二级菜单
	 * @param menu
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年12月23日 下午4:04:40
	 */
	public List<Menu> selectSellerMenuByUserInfoId(Menu menu) {
		return menuDao.selectSellerMenuByUserInfoId(menu);
	}
}
