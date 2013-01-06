package com.dsm.biz;

import java.util.List;

import com.dsm.dao.UserinfoDAO;
import com.dsm.po.Userinfo;



public interface UserInfoBiz {

	public abstract void setIuidao(UserinfoDAO iuidao);

	/**
	 * 显示所有用户信息
	 * 
	 * @return
	 */
	public abstract List<Userinfo> showUserInfo();

	/**
	 * 分页显示用户信息
	 * 
	 * @return
	 */
	public List<Userinfo> showUserInfo(String start, String limit);

	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public int getCount();

	/**
	 * 注册用户
	 * 
	 * @return
	 */
	public boolean addUserInfo(Userinfo userInfo);

	/**
	 * 修改用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfo(Userinfo userInfo);

	/**
	 * 修改用户权限
	 * 
	 * @param userId
	 *            用户id
	 * @param roleId
	 *            权限id
	 * @return
	 */
	public boolean updateUserInfoRole(int userId, int roleId);
	
	/**
	 * 用户登陆
	 * 
	 * @param userId
	 *            用户账号
	 * @return   成功返回UserInfo实体,否则返回null
	 */
	public Userinfo checkLogin(String userId) ;
	
	public boolean deleteUserById(String pkid);
}
