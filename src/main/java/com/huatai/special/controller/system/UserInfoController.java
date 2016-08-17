package com.huatai.special.controller.system;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.MediaTypes;

import com.huatai.special.log.SystemControllerLog;
import com.huatai.special.model.MyPage;
import com.huatai.special.model.UserInfo;
import com.huatai.special.service.UserInfoService;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController extends BaseController{

	private static final Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
			model = setMenuId(model,request);
			MyPage<UserInfo> page = new MyPage<UserInfo>();
			page.setNumber(pageNumber);
			
			String userName = request.getParameter("userName");
			model.addAttribute("userName", userName);
			if(!StringUtils.isEmpty(userName)) {
				page.getParams().put("userName", userName+ "%");
			}
			
			String cityId = request.getParameter("cityId");
			model.addAttribute("cityId", cityId);
			if(!StringUtils.isEmpty(cityId)) {
				page.getParams().put("cityId", cityId);
			}
			
			List<UserInfo> userList = userInfoService.selectUserInfoLimit(page);
			model.addAttribute("userList", userList);
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("UserInfoController err listMenuByParentId", e);
		}
		return "user/userList";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) {
		model = setMenuId(model,request);
		model.addAttribute("action", "addUserInfo");
		return "user/adduser";
	}
	
	@SystemControllerLog(description = "新增用户")
	@RequestMapping(value = "addUserInfo", method = RequestMethod.POST)
	public String addUserInfo(Model model, @ModelAttribute("form") UserInfo userInfo, HttpServletRequest request) {
		model = setMenuId(model,request);
		if(userInfo.getId() == null) {
			if(!StringUtils.isEmpty(userInfo.getUserName())) {
				userInfo.setCreateTime(new Date());
				userInfoService.saveUserInfo(userInfo);
			}
		}
		return "redirect:/userInfo/list";
	}
	
	@SystemControllerLog(description = "修改用户信息")
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	public String updateUserInfo(Model model, @ModelAttribute("form") UserInfo userInfo, HttpServletRequest request) {
		model = setMenuId(model,request);
		if(null != userInfo.getId()) {
			userInfo.setUpdateTime(new Date());
			userInfoService.updateUserInfo(userInfo);
		}
		return "redirect:/userInfo/list";
	}
	

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		model = setMenuId(model,request);
		UserInfo user = userInfoService.getUserInfoById(id);
		model.addAttribute("user", user);
		model.addAttribute("action", "updateUserInfo");
		return "user/adduser";
	}
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		UserInfo user = userInfoService.getUserInfoById(id);
		model.addAttribute("user", user);
		return "user/viewuser";
	}
	
	@SystemControllerLog(description = "重置密码")
	@RequestMapping(value = "resetPwd/{id}", method = RequestMethod.GET)
	public String resetPwd(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		UserInfo user = new UserInfo();
		UserInfo userInfo = userInfoService.getUserInfoById(id);
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		model = setMenuId(model,request);
		user.setId(id);
//		user.setResetPwdFlag("1");
		user.setPlainPassword("123456");
		user.setSalt(userInfo.getSalt());
		userInfoService.resetPwdById(user); 
		return "redirect:/userInfo/list/" + parentId;
	}
	
	/**
	 * 删除单个用户
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月12日 下午5:57:42
	 */
//	@ResponseBody
	@SystemControllerLog(description = "逻辑删除用户")
	@RequestMapping(value = "/deleteUser/{id}")
	public String delete(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		try {
			model = setMenuId(model,request);
			
			userInfoService.deleteUserInfo(id);
		} catch (Exception e) {
			logger.warn("删除用户失败");
		}
		return "redirect:/userInfo/list/";
	}
	
	/**
	 * 判断当前用户名是否存在
	 * @param loginName
	 * @param request
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年11月12日 下午5:13:23
	 */
	@ResponseBody
	@RequestMapping(value="checkExistByUserName",method = RequestMethod.GET,produces=MediaTypes.JSON_UTF_8)
	public Boolean checkExistByUserName(@RequestParam("userName") String loginName,HttpServletRequest request) {
		Boolean flag = true;
		try{
			if(loginName != null && !"".equals(loginName)) {
				if(userInfoService.checkExistByUserName(loginName)){
					flag = false;
				}
				return flag;
			}else {
				return false;
			}
		}catch(Exception e) {
			logger.error("SellerController err checkExistByUserName", e);
			return false;
		}
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUserInfo(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("userInfo", userInfoService.getUserInfoById(id));
		}
	}

}
