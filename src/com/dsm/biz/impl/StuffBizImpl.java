package com.dsm.biz.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dsm.biz.StuffBiz;
import com.dsm.dao.StuffDAO;
import com.dsm.po.Stuff;


public class StuffBizImpl implements StuffBiz{

	StuffDAO istuffDao = null;

	public void setIstuffDao(StuffDAO istuffDao) {
		this.istuffDao= istuffDao;
	}

	/**
	 * 多条件分页模糊查询员工信息
	 * 
	 * @param name
	 *            姓名
	 * @param addDate
	 *            添加日期
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return 当前页的所有数据
	 */
	public List<Stuff> findStuffByPage(String name,String sex,String iden,
			String dep,String wk,String edu,String cardid,String com, 
			String start, String limit) {
        //		 离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Stuff.class);
			
		StringBuffer sbf = new StringBuffer(" 1=1 ");
		if(name !=null && ! "".equals(name))
			sbf.append("and name like '%" + name + "%'");
		if(sex!= null && ! "".equals(sex)){
			sbf.append("and sex='" + sex + "'");
		}
		if(edu!= null && !"".equals(edu)){
			sbf.append("and education='" + edu + "'");
		}
		if(cardid!= null && !"".equals(cardid)){
			sbf.append("and cardid like '%" + cardid + "%'");
		}
		if(dep!= null && !"".equals(dep)){
			sbf.append("and department='" + dep + "'");
		}		
		if(com!= null && !"".equals(com)){
			sbf.append("and comment like '%" + com + "%'");
		}
		if(wk!= null && !"".equals(wk)){
			sbf.append("and wktype='" + wk + "'");
		}
		if(iden != null && !"".equals(iden)){
			sbf.append("and identity like '%" + iden + "%'");
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
		List<Stuff> list = this.istuffDao.findStuffByPage(criteria,Integer.parseInt(start), Integer.parseInt(limit));

		return list;
	}

	/**
	 * 多条件模糊查询员工信息总记录条数
	 * 
	 * @param name
	 *            姓名
	 * @return 给定条件的总记录条数
	 */
	public int getStuffListSize(String name,String sex,String iden,
			String dep,String wk,String edu,String cardid,String com) {
        //		 离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Stuff.class);
		StringBuffer sbf = new StringBuffer(" 1=1 ");
		if(name !=null && ! "".equals(name))
			sbf.append("and name like '%" + name + "%'");
		if(sex!= null && ! "".equals(sex)){
			sbf.append("and sex='" + sex + "'");
		}
		if(edu!= null && !"".equals(edu)){
			sbf.append("and education='" + edu + "'");
		}
		if(cardid!= null && !"".equals(cardid)){
			sbf.append("and cardid like '%" + cardid + "%'");
		}
		if(dep!= null && !"".equals(dep)){
			sbf.append("and department='" + dep + "'");
		}		
		if(com!= null && !"".equals(com)){
			sbf.append("and comment like '%" + com + "%'");
		}
		if(wk!= null && !"".equals(wk)){
			sbf.append("and wktype='" + wk + "'");
		}
		if(iden != null && !"".equals(iden)){
			sbf.append("and identity like '%" + iden + "%'");
		}
		criteria.add(Restrictions.sqlRestriction(sbf.toString()));

		// 调用dao获取查询后的数据
		int result = this.istuffDao.getStuffListSize(criteria);
		return result;
	}

	/**
	 * 变更员工信息
	 * 
	 * @param stuff
	 * @param stuffid
	 * @return
	 */
	public boolean updateStuff(Stuff stuff, String stuffid) {
		try {
			this.istuffDao.update(stuff);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteStuffInfoByPkid(String pkid) {
		try {
			// 获取主键id
			int id = Integer.parseInt(pkid);
			// 获取人员档案心想你实体
			Stuff stuff = this.istuffDao.findById(id);
			this.istuffDao.delete(stuff);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 登记员工档案信息
	 * 
	 * @param Stuff
	 *            人员信息实体
	 * @return 人员信息实体
	 */
	public boolean saveStuff(Stuff stuff) {
		try {
			this.istuffDao.save(stuff);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public List<String> findByItem(String itemName) {
		List<String> list = this.istuffDao.findItemByName(itemName);
		return list;
	}

	public List<Stuff> checkByIdent(String ident) {
		List<Stuff> list = this.istuffDao.findByStuffIdent(ident);
		return list;
	}
	
}
