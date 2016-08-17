package com.huatai.special.controller.system;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.huatai.special.model.City;
import com.huatai.special.model.Menu;
import com.huatai.special.service.CityService;
import com.huatai.special.service.MenuService;
import com.huatai.special.shiro.ShiroDbRealm.ShiroUser;

public abstract class BaseController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	CityService cityService;

	/**
	 * 获取登录后的用户信息
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujianhua  
	 * @throws ParseException 
	 * @createtime 2016年4月8日 下午4:21:53
	 */
	public static ShiroUser getUserInfo(){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;  
	}
/*	
	public static void sessionMeun(){
		System.out.println("加载session中的菜单");
		Session session = SecurityUtils.getSubject().getSession();
		// 获取当前用户所拥有的一级菜单
		Object obj = session.getAttribute("menuList");
		if (null == obj) {
			List<Menu> firstMenuList = menuService.selectMenuByUserInfoId(getUserInfo().id);
			for (Menu menu : firstMenuList) {
				List<Menu> secondMenuList = menuService.selectMenuByParentId(menu.getId());
				menu.setChildrens(secondMenuList);
			}
			session.setAttribute("firstMenuList", firstMenuList);
			
		}
	}*/
	
	/**
	 * 获取当前用户所拥有的一级菜单
	 */
	protected List<Menu> getFirstMenuByUserId() {
		List<Menu> menuList = menuService.selectMenuByUserInfoId(getUserInfo().id);
		return menuList;
	}
	
	/**
	 * 获取当前用户所拥有的二级菜单
	 * @param parentId 
	 * @return
	 */
	protected List<Menu> getSecondMenuByUserId(Long parentId) {
		List<Menu> menuList = menuService.selectMenuByParentId(parentId);
		return menuList;
	}
	
	/**
	 * 菜單處理，讓菜單置於選中狀態
	 * @param model
	 * @param request
	 * @return
	 */
	public Model setMenuId(Model model, HttpServletRequest request){
		String fid = request.getParameter("fid");
		String cid = request.getParameter("cid");
		model.addAttribute("fid", fid);
		model.addAttribute("cid", cid);
		return model;
	}
	
	/**
	 * 获取所有城市信息
	 * @return list<City>
	 */
	public List<City> getCityList(){
		List<City> list = cityService.selectAllCity();
		return list;
	}
}
