package com.dsm.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dsm.dao.StuffDAO;
import com.dsm.po.Stuff;



public class StuffDAOImpl extends HibernateDaoSupport implements StuffDAO{

private static final Log log = LogFactory.getLog(StuffDAOImpl.class);
	
	protected void initDao() {
		// do nothing
	}

	public List<Stuff> findStuffByPage(DetachedCriteria criteria, int start, int limit) {
		List<Stuff> list = getHibernateTemplate().findByCriteria(criteria,
				start, limit);
		return list;
	}

	/**
	 * 多条件查询员工信息集合的数量
	 * 
	 * @param criteria
	 *            离线查询
	 * @return 当前页的所有记录
	 */
	public int getStuffListSize(DetachedCriteria criteria) {
		List<Stuff> list = getHibernateTemplate().findByCriteria(criteria);
		return list.size();
	}

	/**
	 * 更新员工信息
	 * 
	 * @param stuff
	 */
	public void update(Stuff stuff) {
		log.debug("updating Stuff instance");
		try {
			getHibernateTemplate().update(stuff);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Stuff persistentInstance) {
		log.debug("deleting Stuff instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Stuff findById(Integer id) {
		log.debug("getting Stuff instance with id: " + id);
		try {
			Stuff instance = (Stuff) getHibernateTemplate().get(
					Stuff.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void save(Stuff transientInstance) {
		log.debug("saving Stuff instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<String> findItemByName(String columName) {
		log.debug("finding ComItems instance with property: " + columName);
		try {
			if("文化程度".equals(columName))
				columName = "education";
			if("政治面貌".equals(columName))
				columName = "political";
			if("工种".equals(columName))
				columName = "wktype";
			if("部门".equals(columName))
				columName = "department";
			String queryString = "select distinct model." + columName + 
			               " from Stuff as model where " + columName + " is not null";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Stuff> findByStuffIdent(String identity){
		log.debug("finding Stuff instance with StuffId: " + identity);
		String queryString = "from Stuff as model where model.identity='" + identity + "'";
		return getHibernateTemplate().find(queryString);
	}
}
