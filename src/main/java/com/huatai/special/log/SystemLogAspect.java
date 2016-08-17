package com.huatai.special.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huatai.special.model.Menu;
import com.huatai.special.model.Role;
import com.huatai.special.model.SysLog;
import com.huatai.special.model.UserInfo;
import com.huatai.special.service.MenuService;
import com.huatai.special.service.RoleService;
import com.huatai.special.service.SysLogService;
import com.huatai.special.service.UserInfoService;
import com.huatai.special.shiro.ShiroDbRealm.ShiroUser;

/**
 * 切点类
 * @author Administrator
 *
 */
@Aspect    
@Component 
public class SystemLogAspect {
	
	//本地异常日志记录对象    
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	
	@Autowired
	SysLogService sysLogService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	MenuService menuService;
	
	//Service层切点    
    @Pointcut("@annotation(com.huatai.special.log.SystemServiceLog)")    
	public  void serviceAspect() {    
    }    
    
    //Controller层切点    
    @Pointcut("@annotation(com.huatai.special.log.SystemControllerLog)")    
	public  void controllerAspect() {    
    }
    
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */
    @Before("controllerAspect()")    
    public void doBefore(JoinPoint joinPoint) {    
   
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
//       HttpSession session = request.getSession();    
       //读取session中的用户    
//       User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
       //请求的IP    
    	String ip = request.getRemoteAddr();
    	String params = "";
    	if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
    		for (int i = 0; i < joinPoint.getArgs().length; i++) {
//				params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
				params += joinPoint.getArgs()[i] + ";";
			}
    	}
//    	System.out.println(joinPoint.getArgs()[0]);
        try {    
           //*========数据库日志=========*//    
           SysLog log = new SysLog();    
           log.setDescription(getControllerMethodDescription(joinPoint));    
           log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
           log.setLogType(0);    
           log.setRequestIp(ip);    
           log.setExceptionCode( null);    
           log.setExceptionDetail( null);    
           log.setParams(null);    
           log.setCreateUser(user.userName);    
           log.setCreateDate(new Date());    
           //保存数据库    
           sysLogService.insert(log);    
//           System.out.println("=====前置通知结束=====");    
       }  catch (Exception e) {    
           //记录本地异常日志    
           logger.error("==前置通知异常==");    
           logger.error("异常信息:{}", e.getMessage());    
       }    
	}
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
	public String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
//		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
//		Class targetClass = Class.forName(targetName);
		Class targetClass = joinPoint.getTarget().getClass();
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
//		getModelDescription(description, arguments[1]);
		return getModelDescription(description, arguments);
//		if(arguments.length>1){
//			return getModelDescription(description, arguments[1]);
//		}else{
//			return description + ":" + arguments[0];
//		}
	}
	
    private String getModelDescription(String description, Object[] arguments) {
    	String name = null;
		if(!StringUtils.isEmpty(description)){
			if(arguments.length>1){
				if(description.equals("新增用户")){
					UserInfo user = (UserInfo)arguments[1];
					if(null != user.getRoleId()){
						Role role = roleService.getRoleById(user.getRoleId().longValue());
						name = user.getUserName() + ",并且授予'" + role.getName() + "'  角色";
					}else{
						name = user.getUserName();
					}
				}else if (description.equals("修改用户信息") || description.equals("重置密码")){
					UserInfo user = (UserInfo)arguments[1];
					name = user.getUserName();
				}else if (description.equals("新增角色") || description.equals("修改角色信息")){
					Role role = (Role)arguments[1];
					name = "id:" + role.getId() + "  名称：" + role.getName();
				}else if (description.equals("新增菜单") || description.equals("修改菜单信息")){
					Menu menu = (Menu)arguments[1];
					name = "id:" + menu.getId() + "  名称：" + menu.getName();
				}else if(description.equals("逻辑删除用户")){
					Long id = (Long) arguments[0];
					UserInfo user = userInfoService.getUserInfoById(id);
					name = "id:" + id + "  用户名：" + user.getUserName();
				}else if(description.equals("删除角色")){
					Long id = (Long) arguments[0];
					Role role = roleService.getRoleById(id);
					name = "id:" + id + "  角色名：" + role.getName();
				}else if(description.equals("删除菜单")){
					Long id = (Long) arguments[0];
					Menu menu = menuService.getMenuById(id);
					name = "id:" + id + "  菜单名：" + menu.getName();
				}else{
					ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
					name = "操作用户：" + user.userName;
				}
			}else{
				Long id = (Long) arguments[0];
				if(description.equals("逻辑删除用户")){
					UserInfo user = userInfoService.getUserInfoById(id);
					name = "id:" + id + "  用户名：" + user.getUserName();
				}else if(description.equals("删除角色")){
					Role role = roleService.getRoleById(id);
					name = "id:" + id + "  角色名：" + role.getName();
				}else if(description.equals("删除菜单")){
					Menu menu = menuService.getMenuById(id);
					name = "id:" + id + "  菜单名：" + menu.getName();
				}
			}
		}
		return description + "：" + name;
	}

	/**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
		// 读取session中的用户
//		User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
//				params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
				params += joinPoint.getArgs()[i] + ";";
			}
		}
		try {
			/* ==========数据库日志========= */
			SysLog log = new SysLog();   
			log.setDescription(getServiceMthodDescription(joinPoint));
			log.setExceptionCode(e.getClass().getName());
			log.setLogType(1);
			log.setExceptionDetail(e.getMessage());
			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()"));
			log.setParams(params);
			log.setRequestIp(ip);
			log.setCreateUser(user.userName);
	        log.setCreateDate(new Date());    
	           //保存数据库    
	        sysLogService.insert(log);    
//			System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
		/* ==========记录本地异常日志========== */
		logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget()
				.getClass().getName()
				+ joinPoint.getSignature().getName(), e.getClass().getName(),
				e.getMessage(), params);

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint)
			throws Exception {
//		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
//		Class targetClass = Class.forName(targetName);	
		Class targetClass = joinPoint.getTarget().getClass();	
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class)
							.description();
					break;
				}
			}
		}
		return description;
	}
     
     
}
