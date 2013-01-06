package com.dsm.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dsm.dao.DepartDAO;
import com.dsm.po.Depart;




public class DepartDAOImpl extends HibernateDaoSupport implements DepartDAO{

	private static final Log log = LogFactory.getLog(DepartDAOImpl.class);

	protected void initDao() {
		// do nothing
	}
	
	public List<Depart> findDepartByPage(DetachedCriteria criteria, int start,
			int limit) {
		List<Depart> list = getHibernateTemplate().findByCriteria(criteria,
				start, limit);
		return list;
	}

	public int getDepartListSize(DetachedCriteria criteria) {
		List<Depart> list = getHibernateTemplate().findByCriteria(criteria);
		return list.size();
	}

	public void save(Depart transientInstance) {
		log.debug("saving Depart instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(Depart depart) {
		log.debug("updating Depart instance");
		try {
			getHibernateTemplate().update(depart);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Depart findById(Integer id) {
		log.debug("getting Depart instance with id: " + id);
		try {
			Depart instance = (Depart) getHibernateTemplate().get(
					Depart.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Depart instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Depart as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void delete(Depart persistentInstance) {
		log.debug("deleting Depart instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
