package com.huatai.special.controller.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huatai.special.log.SystemControllerLog;
import com.huatai.special.model.City;
import com.huatai.special.model.Menu;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController{
	
	@SystemControllerLog(description="登录成功，进入首页")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) throws IOException {
		Session session = SecurityUtils.getSubject().getSession();
		// 获取当前用户所拥有的一级菜单
		Object obj = session.getAttribute("menuList");
		if (null == obj) {
			List<Menu> firstMenuList = getFirstMenuByUserId();
			for (Menu menu : firstMenuList) {
				List<Menu> secondMenuList = getSecondMenuByUserId(menu.getId());
				menu.setChildrens(secondMenuList);
			}
			session.setAttribute("menuList", firstMenuList);
		}
		//城市信息
		Object cityl = session.getAttribute("cityList");
		if (null == cityl) {
			List<City> cityList = getCityList();
			session.setAttribute("cityList", cityList);
		}
        return "index";
	}

}
