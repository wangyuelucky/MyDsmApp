package com.dsm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dsm.dao.InjureDAO;
import com.dsm.po.Injure;




public class InjureDAOImpl extends HibernateDaoSupport implements InjureDAO{

private static final Log log = LogFactory.getLog(InjureDAOImpl.class);
	
	protected void initDao() {
		// do nothing
	}
	
	public void delete(Injure persistentInstance) {
		log.debug("deleting Injure instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Injure findById(Integer id) {
		log.debug("getting Injure instance with id: " + id);
		try {
			Injure instance = (Injure) getHibernateTemplate().get(
					Injure.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Injure> findInjureByPage(DetachedCriteria criteria, int start,
			int limit) {
		List<Injure> list = getHibernateTemplate().findByCriteria(criteria,
				start, limit);
		return list;
	}

	public int getInjureListSize(DetachedCriteria criteria) {
		List<Injure> list = getHibernateTemplate().findByCriteria(criteria);
		return list.size();
	}

	public void save(Injure transientInstance) {
		log.debug("saving Injure instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(Injure injure) {
		log.debug("updating Injure instance");
		try {
			getHibernateTemplate().update(injure);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<String> findItemByName(String columName) {
		log.debug("finding ComItems instance with property: " + columName);
		try {
			if("工种".equals(columName))
				columName = "wktype";
			if("单位".equals(columName))
				columName = "unit";
			String queryString = "select distinct model." + columName + 
			               " from Injure as model where " + columName + " is not null";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Integer> countInjure() {
		log.debug("finding all Injure count");
		try {
			String queryString = "from Injure where acci_date between '2000-1-1' and '2000-12-31'";
			Integer count = getHibernateTemplate().find(queryString).size();
			List<Integer> counts = new ArrayList<Integer>();
			counts.add(count);
			queryString = "from Injure where acci_date between '2001-1-1' and '2001-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2002-1-1' and '2002-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2003-1-1' and '2003-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2004-1-1' and '2004-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2005-1-1' and '2005-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2006-1-1' and '2006-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2007-1-1' and '2007-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2008-1-1' and '2008-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2009-1-1' and '2009-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			queryString = "from Injure where acci_date between '2010-1-1' and '2010-12-31'";
			count = getHibernateTemplate().find(queryString).size();
			counts.add(count);
			return counts;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Injure> findByInstance(Injure injure) {
		log.debug("finding Injure instance by instance");
		String queryString = "from Injure as model where model.identity='" + 
		injure.getIdentity() + "' and model.acci_date='" + injure.getAcci_date() + 
		"' and model.acci_time='" + injure.getAcci_time() + "'";
		return getHibernateTemplate().find(queryString);
	}

}
