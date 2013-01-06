package com.dsm.dao;

import java.util.List;

import com.dsm.po.Userinfo;



public interface UserinfoDAO {

	public abstract void save(Userinfo userinfo);

	public abstract void delete(Userinfo userinfo);

	public abstract Userinfo findById(java.lang.Integer id);

	public abstract List findByExample(Userinfo userinfo);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract Userinfo merge(Userinfo userinfo);

	public abstract void attachDirty(Userinfo userinfo);

	public abstract void attachClean(Userinfo userinfo);

	/**
	 * 更新
	 * 
	 * @param persistentInstance
	 */
	public void update(Userinfo userinfo);

	/**
	 * 分页查询用户信息
	 * 
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return
	 */
	public List<Userinfo> findByPage(int start, int limit);
	
	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public Object getAllUserInfoCount();
}
