package com.dsm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dsm.po.Injure;



public interface InjureDAO {

	/**
	 * 分页查询工伤档案信息
	 * 
	 * @param criteria
	 *            离线查询
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return 当前页的所有记录
	 */
	public List<Injure> findInjureByPage(DetachedCriteria criteria,int start, int limit);
	
	/**
	 * 多条件查询工伤档案信息集合的数量
	 * 
	 * @param criteria
	 *            离线查询
	 * @return 当前页的所有记录
	 */
	public int getInjureListSize(DetachedCriteria criteria) ;
	
	/**
	 * 更新工伤档案信息
	 * 
	 * @param stuff
	 */
	public void update(Injure injure);
	
	/**
	 * 删除工伤档案信息
	 * 
	 * @param stuff
	 */
	public abstract void delete(Injure persistentInstance);
	
	public abstract Injure findById(java.lang.Integer id);
	
	public abstract void save(Injure transientInstance);
	
	public List<String> findItemByName(String columName);
	
	public List<Integer> countInjure();
	
	public List<Injure> findByInstance(Injure injure);
	
}
