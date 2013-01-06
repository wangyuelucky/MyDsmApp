package com.dsm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dsm.dao.UserinfoDAO;
import com.dsm.po.Userinfo;



public class UserinfoDAOImpl extends HibernateDaoSupport implements UserinfoDAO{

	private static final Log log = LogFactory.getLog(UserinfoDAOImpl.class);

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#save(com.hrm.houfei.po.Userinfo)
	 */
	public void save(Userinfo transientInstance) {
		log.debug("saving Userinfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#delete(com.hrm.houfei.po.Userinfo)
	 */
	public void delete(Userinfo persistentInstance) {
		log.debug("deleting Userinfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 更新
	 * 
	 * @param persistentInstance
	 */
	public void update(Userinfo persistentInstance) {
		log.debug("updateing Userinfo instance");
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
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#findById(java.lang.Integer)
	 */
	public Userinfo findById(java.lang.Integer id) {
		log.debug("getting Userinfo instance with id: " + id);
		try {
			Userinfo instance = (Userinfo) getHibernateTemplate().get(
					"com.dsm.po.Userinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#findByExample(com.hrm.houfei.po.Userinfo)
	 */
	public List findByExample(Userinfo instance) {
		log.debug("finding Userinfo instance by example");
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

	/**
	 * 获取总记录的条数
	 * 
	 * @return
	 */
	public Object getAllUserInfoCount() {
		return (Integer) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Object result = session.createQuery(
								"select count(*) from Userinfo").uniqueResult();
						return result;
					}
				});
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return
	 */
	public List<Userinfo> findByPage(int start, int limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Userinfo.class);
		List<Userinfo> list = getHibernateTemplate().findByCriteria(criteria,
				start, limit);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#findByProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Userinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userinfo as model where model."
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
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#findAll()
	 */
	public List findAll() {
		log.debug("finding all Userinfo instances");
		try {
			String queryString = "from Userinfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#merge(com.hrm.houfei.po.Userinfo)
	 */
	public Userinfo merge(Userinfo detachedInstance) {
		log.debug("merging Userinfo instance");
		try {
			Userinfo result = (Userinfo) getHibernateTemplate().merge(
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
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#attachDirty(com.hrm.houfei.po.Userinfo)
	 */
	public void attachDirty(Userinfo instance) {
		log.debug("attaching dirty Userinfo instance");
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
	 * @see com.hrm.houfei.dao.impl.IUserInfoDAO#attachClean(com.hrm.houfei.po.Userinfo)
	 */
	public void attachClean(Userinfo instance) {
		log.debug("attaching clean Userinfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserinfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserinfoDAO) ctx.getBean("UserinfoDAO");
	}

}
