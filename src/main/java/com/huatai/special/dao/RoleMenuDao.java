/**
 * @type com.yuedi.web.dao.impl.RoleUserInfoDaoImpl
 */
package com.huatai.special.dao;

import java.util.List;

import com.huatai.special.common.MyBatisRepository;
import com.huatai.special.model.RoleMenu;

/**
 * @author sky
 *
 * @date 2014年2月14日
 *
 */
@MyBatisRepository
public interface RoleMenuDao {
	
	public int insertRoleMenu(List<RoleMenu> list);	

	public int deleteRoleMenuByMenuId(Long menuId);
	
	public int deleteRoleMenuByRoleId(Long roleId);
}
