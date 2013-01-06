package com.dsm.biz.impl;

import java.util.List;

import com.dsm.biz.UserRoleBiz;
import com.dsm.dao.UserRoleDAO;
import com.dsm.po.Userrole;


public class UserRoleBizImpl implements UserRoleBiz{
	
    //	 用户权限dao接口
	private UserRoleDAO iurdao = null;

	public void setIurdao(UserRoleDAO iurdao) {
		this.iurdao = iurdao;
	}

	public UserRoleBizImpl() {
		super();
	}

	/**
	 * 分页显示所有用户角色信息
	 * 
	 * @return
	 */
	public List<Userrole> showAllUserRolesByPage(String index, String pageSize) {
		if (null == index) {
			index = "0";
		}
		if (null == pageSize) {
			pageSize = "10";
		}
		// 分页获取用户角色信息
		List<Userrole> list = iurdao.findByPage(Integer.parseInt(index),
				Integer.parseInt(pageSize));
		return list;
	}

	/**
	 * 显示所有用户角色信息
	 * @return
	 */
	public List<Userrole> showAllUserRoles() {
		List<Userrole> list = iurdao.findAll();
		return list;
	}

	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public int getUserRolesCount() {
		return this.iurdao.getAllRowsCount();
	}

	/**
	 * 添加用户角色
	 * 
	 * @param urInstance
	 * @return
	 */
	public boolean addUserRole(Userrole urInstance) {
		try {
			iurdao.save(urInstance);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 更新用户角色
	 * 
	 * @param urInstance
	 * @return
	 */
	public boolean updateUserRole(Userrole urInstance) {
		try {
			this.iurdao.update(urInstance);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
