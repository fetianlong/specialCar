/**
 * @type com.yuedi.web.entity.Menu
 */
package com.huatai.special.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author sky
 *
 * @date 2014年1月28日
 *
 */
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long parentId;
	private String name; 
	private String url;
	private String newUrl;
	private Integer ordIndex;
	private Boolean isDeleteFlag;
	private Long createrId;
	private String createrName;
	private String menuType;
	private Date createDateTime;
	private Integer issued;
	private Long parentParamId;
	private Long netWork;
	private List childrens;
	
	
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
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getNewUrl() {
		return newUrl;
	}
	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}
	/**
	 * @return the ordIndex
	 */
	public Integer getOrdIndex() {
		return ordIndex;
	}
	/**
	 * @param ordIndex the ordIndex to set
	 */
	public void setOrdIndex(Integer ordIndex) {
		this.ordIndex = ordIndex;
	}
	/**
	 * @return the isDeleteFlag
	 */
	public Boolean getIsDeleteFlag() {
		return isDeleteFlag;
	}
	/**
	 * @param isDeleteFlag the isDeleteFlag to set
	 */
	public void setIsDeleteFlag(Boolean isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}
	/**
	 * @return the createrId
	 */
	public Long getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Integer getIssued() {
		return issued;
	}
	public void setIssued(Integer issued) {
		this.issued = issued;
	}
	public Long getParentParamId() {
		return parentParamId;
	}
	public void setParentParamId(Long parentParamId) {
		this.parentParamId = parentParamId;
	}
	public Long getNetWork() {
		return netWork;
	}
	public void setNetWork(Long netWork) {
		this.netWork = netWork;
	}
	public List getChildrens() {
		return childrens;
	}
	public void setChildrens(List childrens) {
		this.childrens = childrens;
	}
}
