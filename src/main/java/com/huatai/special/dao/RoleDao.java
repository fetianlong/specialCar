/**
 * @type com.yuedi.web.dao.RoleDao
 */
package com.huatai.special.dao;

import java.util.List;

import com.huatai.special.common.MyBatisRepository;
import com.huatai.special.model.MyPage;
import com.huatai.special.model.Role;

/**
 * @author sky
 *
 * @date 2014年2月12日
 *
 */
@MyBatisRepository
public interface RoleDao {
	
	/**
	 * 添加角色
	 * @param role
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:16:38
	 */
	public long insertRole(Role role);
	
	/**
	 * 修改权限
	 * @param role
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月15日 下午5:18:37
	 */
	public int updateRoleById(Role role);
	
	
	public int deleteRoleById(Long id);
	public List<Role> selectAllRole();
	
	/**
	 * 给角色增加资源权限
	 * @param llist
	 * @param roleId
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年4月14日 下午7:33:03
	 */
	public int addRoleMenu(String llist, String roleId);
	public Role selectRoleById(Long roleId);
	
	/**
	 * 根据机构ID获取所有的角色信息
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月16日 下午5:54:10
	 */
	public List<Role> findMyRole(Long userFranchiseesId);
	/**
	 * 根据用户id查询角色信息
	 * @param userId
	 * @return
	 */
	public List<Role> selectRoleByUserId(Long userId);
	
	/**
	 * 分页获取角色数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 上午10:49:52
	 */
	public List<Role> selectRoleLimit(MyPage<Role> page);
}
