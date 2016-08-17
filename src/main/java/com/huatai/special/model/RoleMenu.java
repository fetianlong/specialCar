/**
 * @type com.yuedi.web.entity.Menu
 */
package com.huatai.special.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sky
 *
 * @date 2014年1月28日
 *
 */
public class RoleMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long roleId;
	private Long menuId;
	private Long netWork;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getNetWork() {
		return netWork;
	}
	public void setNetWork(Long netWork) {
		this.netWork = netWork;
	} 
}
