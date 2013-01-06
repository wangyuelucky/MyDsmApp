package com.dsm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dsm.po.Depart;



public interface DepartDAO {

	/**
	 * 分页查询部门信息
	 * 
	 * @param criteria
	 *            离线查询
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return 当前页的所有记录
	 */
	public List<Depart> findDepartByPage(DetachedCriteria criteria,int start, int limit);
	
	/**
	 * 多条件查询部门信息集合的数量
	 * 
	 * @param criteria
	 *            离线查询
	 * @return 当前页的所有记录
	 */
	public int getDepartListSize(DetachedCriteria criteria) ;
	
	public abstract void save(Depart transientInstance);
	
	public void update(Depart depart);
	
	public abstract Depart findById(java.lang.Integer id);
	
	public List findByProperty(String propertyName, Object value);
	
	public abstract void delete(Depart persistentInstance);
}
