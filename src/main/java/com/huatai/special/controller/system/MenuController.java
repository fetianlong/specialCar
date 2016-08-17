package com.huatai.special.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatai.special.log.SystemControllerLog;
import com.huatai.special.model.Menu;
import com.huatai.special.service.MenuService;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController{
	@Autowired
	private MenuService menuService;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request){
		try{
			model = setMenuId(model,request);
			
			List<Menu> menusList = new ArrayList<Menu>();
			String parentMenuId = request.getParameter("parentMenuId");

			if(!StringUtils.isEmpty(parentMenuId)){
				List<Menu> menus = menuService.getMyMenuByParentId(new Long(parentMenuId));
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
				menusList = list;
			}else{
				menusList = menuService.selectAllMenu();
			}
			model.addAttribute("parentMenuId", parentMenuId);
			model.addAttribute("menusList", menusList);
			
			List<Menu> parentMenuList = new ArrayList<Menu>();
			
			parentMenuList = menuService.findParentMenu();
			model.addAttribute("parentMenuList", parentMenuList);
			
		}catch(Exception e) {
			logger.error("MenuController err list", e);
		}
		
		return "menu/menuList";
	}
	
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request){
		model = setMenuId(model,request);
		
		model.addAttribute("action", "addMenu");
		
		List<Menu> parentMenuList = menuService.findParentMenu();
		model.addAttribute("parentMenuList", parentMenuList);
		return "menu/menuForm";
	}
	
	@SystemControllerLog(description = "新增菜单")
	@RequestMapping(value="addMenu",method=RequestMethod.POST)
	public String addMenu(Model model, @ModelAttribute("form") Menu menu, HttpServletRequest request) {
		model = setMenuId(model,request);
		
		if(menu.getId() == null) {
			if(!StringUtils.isEmpty(menu.getName())) {
				menu.setCreateDateTime(new Date());
				menu.setCreaterId(super.getUserInfo().id);
				menu.setCreaterName(super.getUserInfo().userName);
				menu.setIsDeleteFlag(false);
				menuService.addMenu(menu);
			}
		}
		
		return "redirect:/menu/list";
	}
	
	@SystemControllerLog(description = "修改菜单信息")
	@RequestMapping(value="updateMenu",method=RequestMethod.POST)
	public String updateMenu(Model model, @ModelAttribute("form") Menu menu, HttpServletRequest request) {
		model = setMenuId(model,request);
			
		menuService.updateMenu(menu);
		return "redirect:/menu/list";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		try{
			model = setMenuId(model,request);
			
			String parentId = request.getParameter("parentId");
			model.addAttribute("parentId", parentId);
			model.addAttribute("action", "updateMenu");
			
			Menu menu = menuService.getMenuById(id);
			model.addAttribute("menu", menu);
			
			List<Menu> parentMenuList = menuService.findParentMenu();
//			List<Menu> parentMenuList = menuService.selectAllMenuIsDeleteFlag();
			model.addAttribute("parentMenuList", parentMenuList);
		}catch(Exception e) {
			logger.error("MenuController err updateForm", e);
		}
		
		return "menu/menuForm";
	}
	
	@SystemControllerLog(description = "删除菜单")
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		model = setMenuId(model,request);
		
		menuService.deleteMenuById(id);
		return "redirect:/menu/list";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "queryMenuAll/{roleId}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<Object> queryMenuAll(@PathVariable("roleId") Long roleId,Model model) {
		List<Object> menuList = new ArrayList<Object>();
		try {
			List<Menu> menus = menuService.findAllMenuIsDeleteFlag();
			List<Menu> myMenus = menuService.findAllMenuByRole(roleId);	//当前角色所拥有的菜单资源
			
			if (menus != null && menus.size() > 0) {
				for (int i = 0; i < menus.size(); i++) {
					Map menuMap = new HashMap();
					Menu menu = menus.get(i);
					
					menuMap.put("id", menu.getId());
					if (menu.getParentId() == null) {
						menuMap.put("pId", 0);
						menuMap.put("open", true);
					}else{
						menuMap.put("pId", menu.getParentId());
					}
					
					menuMap.put("name", menu.getName());
					
					for (int j = 0; j < myMenus.size(); j++) {
						Menu mymenu = myMenus.get(j);
						if(menu.getId().equals(mymenu.getId())){
							menuMap.put("checked", true);
						}
					}
					menuList.add(menuMap);
				}
			}
		} catch (Exception e) {
			logger.error("MenuController err queryMenuAll", e);
		}
		return menuList;
	}
}
