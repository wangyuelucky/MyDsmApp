package com.dsm.biz;

import java.util.List;

import com.dsm.po.Userrole;

public interface UserRoleBiz {

	/**
	 * 显示所有用户角色信息
	 * 
	 * @return
	 */
	public List<Userrole> showAllUserRoles();

	/**
	 * 分页显示所有用户角色信息
	 * 
	 * @return
	 */
	public List<Userrole> showAllUserRolesByPage(String start, String limit);

	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public int getUserRolesCount();

	/**
	 * 添加用户角色
	 * 
	 * @param urInstance
	 * @return
	 */
	public boolean addUserRole(Userrole urInstance);

	/**
	 * 更新用户角色
	 * 
	 * @param urInstance
	 * @return
	 */
	public boolean updateUserRole(Userrole urInstance);
	
}
