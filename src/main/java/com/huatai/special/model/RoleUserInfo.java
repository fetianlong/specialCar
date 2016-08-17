/**
 * @type com.yuedi.web.entity.RoleUserInfo
 */
package com.huatai.special.model;

import java.io.Serializable;

/**
 * @author sky
 *
 * @date 2014年2月14日
 *
 */
public class RoleUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userInfoId;
	private Long roleId;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userInfoId
	 */
	public Long getUserInfoId() {
		return userInfoId;
	}
	/**
	 * @param userInfoId the userInfoId to set
	 */
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
