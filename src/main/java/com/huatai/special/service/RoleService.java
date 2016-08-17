package com.huatai.special.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huatai.special.dao.RoleDao;
import com.huatai.special.dao.RoleMenuDao;
import com.huatai.special.dao.RoleUserInfoDao;
import com.huatai.special.model.MyPage;
import com.huatai.special.model.Role;
import com.huatai.special.model.RoleMenu;
import com.huatai.special.model.RoleUserInfo;

/**
 * @author sky
 *
 * @date 2014年2月12日
 *
 */
@Component
@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleUserInfoDao roleUserInfoDao;
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	/**
	 * 添加角色
	 * @param role
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:16:38
	 */
	public long addRole(Role role) {
		return roleDao.insertRole(role);
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
	public int updateRole(Role role) {
		return roleDao.updateRoleById(role);
	}

	/**
	 * 删除角色，并删除角色与用户和菜单的关联关系
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年4月15日 上午10:33:58
	 */
	public int deleteRoleById(Long id) {
		if (isSupervisor(id)) {
			throw new ServiceException("不能删除超级管理员角色");
		}
		
		//删除用户角色关联关系
		roleUserInfoDao.deleteRoleUserByRoleId(id);
		//删除角色菜单关联关系
		roleMenuDao.deleteRoleMenuByRoleId(id);
		//最后删除角色
		return roleDao.deleteRoleById(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}
	
	/**
	 * 获取所有角色
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:20:42
	 */
	public List<Role> findAllRole() {
		return roleDao.selectAllRole();
	}

	/**
	 * 给角色增加资源权限
	 * @param idList	菜单id list
	 * @param roleId	角色ID
	 * @return  
	 * @description   增加前，先删除原来角色的菜单资源，然后在判断idList是否为空来进行增加
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年4月15日 上午11:07:39
	 */
	public int addRoleMenu(String llist, String roleId) {
		int r = 0;
		if (!StringUtils.isEmpty(roleId)) {
			r = roleMenuDao.deleteRoleMenuByRoleId(Long.parseLong(roleId));
			if(!StringUtils.isEmpty(llist)){
				String[] ll = llist.split(",");
				List<RoleMenu> list = new ArrayList<RoleMenu>();
				for (int i = 0; i < ll.length; i++) {
					RoleMenu rm = new RoleMenu();
					rm.setRoleId(Long.parseLong(roleId));
					rm.setMenuId(new Long(ll[i]));
					rm.setNetWork(2L);
					list.add(rm);
				}
				r = roleMenuDao.insertRoleMenu(list);
			}
		}
		
		return r;
	}

	/**
	 * 根据Id获取角色信息
	 * @param roleId
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:21:12
	 */
	public Role getRoleById(Long roleId) {
		return roleDao.selectRoleById(roleId);
	}

	/**
	 * 根据机构ID获取所有的角色信息
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月16日 下午5:54:10
	 */
	public List<Role> findMyRole(Long userFranchiseesId) {
		return roleDao.findMyRole(userFranchiseesId);
	}

	/**
	 * 根据用户id查询角色信息
	 * @param userId
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:21:44
	 */
	public List<Role> selectRoleByUserId(Long userId) {
		return roleDao.selectRoleByUserId(userId);
	}
	
	/**
	 * 分页获取角色数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	public List<Role> selectRoleLimit(MyPage<Role> page) {
		return roleDao.selectRoleLimit(page);
	}
	
	/**
	 * 角色分配给用户
	 * @param roleUserInfo
	 * @return
	 */
	public long assignUserToRole(RoleUserInfo roleUserInfo) {
		return roleUserInfoDao.insertRoleUserInfo(roleUserInfo);
	}
	
	/**
	 * 删除角色分配给用户
	 * @param roleUserInfo
	 * @return
	 */
	public int removeUserFromRole(Long roleUserInfoId) {
		return roleUserInfoDao.deleteRoleUserInfoById(roleUserInfoId);
	}
	
}
