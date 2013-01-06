package com.dsm.biz.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dsm.biz.InjureBiz;
import com.dsm.dao.InjureDAO;
import com.dsm.po.Injure;



public class InjureBizImpl implements InjureBiz{

	InjureDAO iinjureDao = null;
	
	public void setIinjureDao(InjureDAO iinjureDao) {
		this.iinjureDao = iinjureDao;
	}

	public boolean deleteInjureInfoByPkid(String pkid) {
		try {
			// 获取主键id
			int id = Integer.parseInt(pkid);
			// 获取人员档案心想你实体
			Injure injure = this.iinjureDao.findById(id);
			this.iinjureDao.delete(injure);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Injure> findInjureByPage(String name,String wktype,
			String unit,String accidate, String start, String limit) {
		 //		 离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Injure.class);

		StringBuffer sbf = new StringBuffer(" 1=1 ");
		if(name !=null && !"".equals(name))
			sbf.append("and name like '%" + name + "%'");
		if(wktype!= null && ! "".equals(wktype)){
			sbf.append("and wktype='" + wktype + "'");
		}
		if(unit!= null && !"".equals(unit)){
			sbf.append("and unit='" + unit + "'");
		}
		if(accidate!= null && !"".equals(accidate)){
			String acci = accidate.substring(0, 10);
			sbf.append("and acci_date='" + accidate + "'");
		}
		criteria.add(Restrictions.sqlRestriction(sbf.toString()));
		
		// 为分页参数设置默认值
		if ("".equals(start) || null == start) {
			start = "0";
		}
		if ("".equals(limit) || null == limit) {
			limit = "10";
		}

		// 调用dao获取查询后的数据
		List<Injure> list = this.iinjureDao.findInjureByPage(criteria,Integer.parseInt(start), Integer.parseInt(limit));

		return list;
	}

	public int getInjureListSize(String name,String wktype,
			String unit,String accidate) {
		 //		 离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Injure.class);

		StringBuffer sbf = new StringBuffer(" 1=1 ");
		if(name !=null && !"".equals(name))
			sbf.append("and name like '%" + name + "%'");
		if(wktype!= null && ! "".equals(wktype)){
			sbf.append("and wktype='" + wktype + "'");
		}
		if(unit!= null && !"".equals(unit)){
			sbf.append("and unit='" + unit + "'");
		}
		if(accidate!= null && !"".equals(accidate)){
			String acci = accidate.substring(0, 10);
			sbf.append("and acci_date='" + accidate + "'");
		}
		criteria.add(Restrictions.sqlRestriction(sbf.toString()));

		// 调用dao获取查询后的数据
		int result = this.iinjureDao.getInjureListSize(criteria);
		return result;
	}

	public boolean saveInjure(Injure injure) {
		try {
			this.iinjureDao.save(injure);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean updateInjure(Injure injure, String injureid) {
		try {
			this.iinjureDao.update(injure);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> findByItem(String itemName) {
		List<String> list = this.iinjureDao.findItemByName(itemName);
		return list;
	}

	public List<Integer> countInjure() {
		return this.iinjureDao.countInjure();
	}

	public List<Injure> checkByInstance(Injure instance) {
		List<Injure> list = this.iinjureDao.findByInstance(instance);
		return list;
	}

}
