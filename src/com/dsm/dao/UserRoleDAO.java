package com.dsm.dao;

import java.util.List;

import com.dsm.po.Userrole;


public interface UserRoleDAO {

	public abstract void save(Userrole userrole);

	public abstract void delete(Userrole userrole);

	public abstract Userrole findById(java.lang.Integer id);

	public abstract List findByExample(Userrole userrole);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract Userrole merge(Userrole userrole);

	public abstract void attachDirty(Userrole userrole);

	public abstract void attachClean(Userrole userrole);

	/**
	 * 更新用户角色
	 * 
	 * @param persistentInstance
	 * @return
	 */
	public void update(Userrole userrole);

	/**
	 * 分页查询
	 * 
	 * @param start
	 *            索引
	 * @param limit
	 *            每页要显示的记录
	 * @return Det
	 */
	public List<Userrole> findByPage(int start, int limit);
	
	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public int getAllRowsCount();
	
}
