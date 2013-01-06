package com.dsm.biz.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.dsm.biz.DepartBiz;
import com.dsm.dao.DepartDAO;
import com.dsm.po.Depart;


public class DepartBizImpl implements DepartBiz{
	
	DepartDAO idepartDao = null;

	public void setIdepartDao(DepartDAO idepartDao) {
		this.idepartDao = idepartDao;
	}

	public List<Depart> findDepartByPage(String name, String start, String limit) {
		 //		 离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Depart.class);
		// 如果条件姓名不为空,那么将条件姓名附加上
		if (!"".equals(name) && null != name) {
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}

		// 为分页参数设置默认值
		if ("".equals(start) || null == start) {
			start = "0";
		}
		if ("".equals(limit) || null == limit) {
			limit = "10";
		}

		// 调用dao获取查询后的数据
		List<Depart> list = this.idepartDao.findDepartByPage(criteria,Integer.parseInt(start), Integer.parseInt(limit));

		return list;
	}

	public int getDepartListSize(String name) {
		 //		 离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Depart.class);
		// 如果条件姓名不为空,那么将条件姓名附加上
		if (!"".equals(name) && null != name) {
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}

		// 调用dao获取查询后的数据
		int result = this.idepartDao.getDepartListSize(criteria);
		return result;
	}

	public boolean saveDepartFile(Depart depart) {
		try{
			this.idepartDao.save(depart);
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}

	public boolean updateDepartFile(Depart depart, String pkid) {
		try{
			//Depart dp = this.idepartDao.findById(Integer.parseInt(pkid));
			int id = Integer.parseInt(pkid);
			depart.setId(id);
			this.idepartDao.update(depart);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	public List<Depart> findDepartByName(String propertyName, String value) {
		return this.idepartDao.findByProperty(propertyName, value);
	}

	public boolean deleteDepartInfoByPkid(String pkid) {
		try {
			// 获取主键id
			int id = Integer.parseInt(pkid);
			// 获取人员档案心想你实体
			Depart depart = this.idepartDao.findById(id);
			this.idepartDao.delete(depart);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
