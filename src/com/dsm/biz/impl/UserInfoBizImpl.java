package com.dsm.biz.impl;

import java.util.Calendar;
import java.util.List;

import com.dsm.biz.UserInfoBiz;
import com.dsm.dao.UserinfoDAO;
import com.dsm.po.Userinfo;
import com.dsm.po.Userrole;



public class UserInfoBizImpl implements UserInfoBiz{
	
	UserinfoDAO iuidao = null;
	
	public void setIuidao(UserinfoDAO iuidao) {
		this.iuidao = iuidao;
		
	}
	
	public UserInfoBizImpl() {
		super();
	}

	/**
	 * 显示所有用户信息
	 * 
	 * @return
	 */
	public List<Userinfo> showUserInfo() {
		List<Userinfo> list = this.iuidao.findAll();
		return list;
	}

	/**
	 * 分页显示用户信息
	 * 
	 * @return
	 */
	public List<Userinfo> showUserInfo(String start, String limit) {
		if (start == null || "".equals(start)) {
			start = "0";
		}
		if (limit == null || "".equals(limit)) {
			limit = "5";
		}
		List<Userinfo> list = this.iuidao.findByPage(Integer.parseInt(start),
				Integer.parseInt(limit));
		return list;
	}

	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public int getCount() {
		Object obj = this.iuidao.getAllUserInfoCount();
		int count = Integer.parseInt(obj.toString());
		return count;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfo(Userinfo userInfo) {
		try {
			Userinfo user = this.iuidao.findById(userInfo.getUserId());
			// 密码
			user.setUserPass(userInfo.getUserPass());
			// 真是姓名
			user.setUserTrueName(userInfo.getUserTrueName());
			// 备注
			user.setUserMemo(userInfo.getUserMemo());
			// 权限
			user.setUserrole(userInfo.getUserrole());
			//System.out.println("userinfobizImpl userrole " + userInfo.getUserrole() + "roleid " + userInfo.getUserrole().getUrId() );
			// 更新
			this.iuidao.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 修改用户权限
	 * 
	 * @param userId
	 *            用户id
	 * @param roleId
	 *            权限id
	 * @return
	 */
	public boolean updateUserInfoRole(int userId, int roleId) {
		try {
			Userinfo user = this.iuidao.findById(userId);
			Userrole ur = user.getUserrole();
			ur.setUrId(roleId);
			// 更新
			this.iuidao.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 注册用户
	 * 
	 * @return
	 */
	public boolean addUserInfo(Userinfo userInfo) {
		try {
			Calendar cal = Calendar.getInstance();
			userInfo.setUserCreateDate(cal.getTime());
			this.iuidao.save(userInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 用户登陆
	 * 
	 * @param userId
	 *            用户账号
	 * @return 登陆成功返回UserInfo实体,否则返回null
	 */
	public Userinfo checkLogin(String userId) {
		List<Userinfo> list = this.iuidao.findByProperty("userAccount", userId);
		if (list.size() > 0) {
			Userinfo ui = list.get(0);
			return ui;
		}
		return null;
	}

	public boolean deleteUserById(String pkid) {
		try {
			// 获取主键id
			int id = Integer.parseInt(pkid);
			// 获取人员档案心想你实体
			Userinfo user = this.iuidao.findById(id);
			this.iuidao.delete(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
