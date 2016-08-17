/**
 * @type com.yuedi.web.dao.impl.RoleUserInfoDaoImpl
 */
package com.huatai.special.dao;

import com.huatai.special.common.MyBatisRepository;
import com.huatai.special.model.RoleUserInfo;

/**
 * @author sky
 *
 * @date 2014年2月14日
 *
 */
@MyBatisRepository
public interface RoleUserInfoDao {
	
	public long insertRoleUserInfo(RoleUserInfo roleUserInfo);	

	public int deleteRoleUserInfoById(Long roleUserInfoId);
	
	public int deleteRoleUserByRoleId(Long roleId);
}
