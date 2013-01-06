package com.dsm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dsm.po.Stuff;


public interface StuffDAO {

	/**
	 * 分页查询员工信息
	 * 
	 * @param criteria
	 *            离线查询
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return 当前页的所有记录
	 */
	public List<Stuff> findStuffByPage(DetachedCriteria criteria,int start, int limit);
	
	/**
	 * 多条件查询人员档案信息集合的数量
	 * 
	 * @param criteria
	 *            离线查询
	 * @return 当前页的所有记录
	 */
	public int getStuffListSize(DetachedCriteria criteria) ;
	
	/**
	 * 更新员工信息
	 * 
	 * @param stuff
	 */
	public void update(Stuff stuff);
	
	/**
	 * 更新员工信息
	 * 
	 * @param stuff
	 */
	public abstract void delete(Stuff persistentInstance);
	
	public abstract Stuff findById(java.lang.Integer id);
	
	public abstract void save(Stuff transientInstance);
	
	public List<String> findItemByName(String columName);
	
	public List<Stuff> findByStuffIdent(String identity);
}
