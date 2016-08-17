package com.huatai.special.shiro;

import java.io.Serializable;
import java.security.Permission;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.Encodes;

import com.google.common.base.Objects;
import com.huatai.special.model.Menu;
import com.huatai.special.model.UserInfo;
import com.huatai.special.service.MenuService;
import com.huatai.special.service.UserInfoService;

public class ShiroDbRealm extends AuthorizingRealm {

	protected UserInfoService userInfoService;
	@Autowired
	private MenuService menuService;
	
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		try {
			UserInfo user = userInfoService.selectUserInfoByUserName(token.getUsername());
			if (user != null) {
				byte[] salt = Encodes.decodeHex(user.getSalt());
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
						new ShiroUser(user.getId(), user.getUserName(), user.getPhone(),user.getCity(),user.getEmail(),user.getCityId(),user.getName(),user.getCreateTime(),user.getUpdateTime()),
						user.getPassword(), ByteSource.Util.bytes(salt), getName());
				
				Session session = SecurityUtils.getSubject().getSession();
				// 获取当前用户所拥有的一级菜单
				Object obj = session.getAttribute("menuList");
				if (null == obj) {
					List<Menu> firstMenuList = menuService.selectMenuByUserInfoId(user.getId());
					for (Menu menu : firstMenuList) {
						List<Menu> secondMenuList = menuService.selectMenuByParentId(menu.getId());
						menu.setChildrens(secondMenuList);
					}
					session.setAttribute("menuList", firstMenuList);
				}
				return authenticationInfo;
			}else{
				logger.warn("[方法：" + new Throwable().getStackTrace()[1].getMethodName() +"] - 参数【"+token.getUsername()+"】 -错误信息：");
				return null;
			}
		} catch (Exception e) {
			logger.warn("[方法：" + new Throwable().getStackTrace()[1].getMethodName()+"] - 参数【"+token.getUsername()+"】 -错误信息：" + e.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		UserInfo user = userInfoService.selectUserInfoByUserName(shiroUser.userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addRoles(null);
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
//		setCredentialsMatcher(new CustomCredentialsMatcher());
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserInfoService.HASH_ALGORITHM);
		matcher.setHashIterations(UserInfoService.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
		
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}


	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Long id;
		public String userName;
		public String phone;
		public String email;
		public String name;
		public Long cityId;
		public String city;
		public Date createTime;
		public Date updateTime;
		
		// public ShiroUser(Long id, String username, String phone) {
		// this.id = id;
		// this.username = username;
		// this.phone = phone;
		// }

		public ShiroUser(Long id, String userName, String phone, String email,
				String name, Long cityId, String city, Date createTime,
				Date updateTime) {
			super();
			this.id = id;
			this.userName = userName;
			this.phone = phone;
			this.email = email;
			this.name = name;
			this.cityId = cityId;
			this.city = city;
			this.createTime = createTime;
			this.updateTime = updateTime;
		}

		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Long getCityId() {
			return cityId;
		}


		public void setCityId(Long cityId) {
			this.cityId = cityId;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public Date getCreateTime() {
			return createTime;
		}


		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}


		public Date getUpdateTime() {
			return updateTime;
		}


		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}


		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return userName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(userName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (userName == null) {
				if (other.userName != null) {
					return false;
				}
			} else if (!userName.equals(other.userName)) {
				return false;
			}
			return true;
		}
	}
}
