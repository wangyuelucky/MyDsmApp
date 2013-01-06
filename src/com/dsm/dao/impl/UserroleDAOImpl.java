package com.dsm.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dsm.dao.UserRoleDAO;
import com.dsm.po.Userrole;


public class UserroleDAOImpl extends HibernateDaoSupport implements UserRoleDAO{

	private static final Log log = LogFactory.getLog(UserroleDAOImpl.class);

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#save(com.hrm.houfei.po.Userrole)
	 */
	public void save(Userrole transientInstance) {
		log.debug("saving Userrole instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param start
	 *            索引
	 * @param limit
	 *            每页要显示的记录
	 * @return Det
	 */
	public List<Userrole> findByPage(int start, int limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Userrole.class);
		List<Userrole> list = super.getHibernateTemplate().findByCriteria(
				criteria, start, limit);
		return list;
	}

	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public int getAllRowsCount() {
		return this.findAll().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#delete(com.hrm.houfei.po.Userrole)
	 */
	public void delete(Userrole persistentInstance) {
		log.debug("deleting Userrole instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 更新用户角色
	 * 
	 * @param persistentInstance
	 * @return
	 */
	public void update(Userrole persistentInstance) {
		log.debug("updateting Userrole instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#findById(java.lang.Integer)
	 */
	public Userrole findById(java.lang.Integer id) {
		log.debug("getting Userrole instance with id: " + id);
		try {
			Userrole instance = (Userrole) getHibernateTemplate().get(
					Userrole.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#findByExample(com.hrm.houfei.po.Userrole)
	 */
	public List findByExample(Userrole instance) {
		log.debug("finding Userrole instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#findByProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Userrole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userrole as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#findAll()
	 */
	public List findAll() {
		log.debug("finding all Userrole instances");
		try {
			String queryString = "from Userrole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#merge(com.hrm.houfei.po.Userrole)
	 */
	public Userrole merge(Userrole detachedInstance) {
		log.debug("merging Userrole instance");
		try {
			Userrole result = (Userrole) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#attachDirty(com.hrm.houfei.po.Userrole)
	 */
	public void attachDirty(Userrole instance) {
		log.debug("attaching dirty Userrole instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserRoleDAO#attachClean(com.hrm.houfei.po.Userrole)
	 */
	public void attachClean(Userrole instance) {
		log.debug("attaching clean Userrole instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserRoleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserRoleDAO) ctx.getBean("UserroleDAO");
	}



}
