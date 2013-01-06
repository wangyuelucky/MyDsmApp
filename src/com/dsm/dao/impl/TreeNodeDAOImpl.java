package com.dsm.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dsm.dao.TreeNodeDAO;
import com.dsm.po.TreeNode;


public class TreeNodeDAOImpl extends HibernateDaoSupport implements TreeNodeDAO{
	private static final Log log = LogFactory.getLog(TreeNodeDAOImpl.class);
	
	protected void initDao() {
		// do nothing
	}

	public void attachClean(TreeNode treenode) {
		log.debug("attaching clean TreeNode instance");
		try {
			getHibernateTemplate().lock(treenode, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachDirty(TreeNode treenode) {
		log.debug("attaching dirty TreeNode instance");
		try {
			getHibernateTemplate().saveOrUpdate(treenode);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TreeNode treenode) {
		log.debug("deleting TreeNode instance");
		try {
			getHibernateTemplate().delete(treenode);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TreeNode instances");
		try {
			String queryString = "from TreeNode";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByExample(TreeNode treenode) {
		log.debug("finding TreeNode instance by example");
		try {
			List results = getHibernateTemplate().findByExample(treenode);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public TreeNode findById(Integer id) {
		log.debug("getting TreeNode instance with id: " + id);
		try {
			TreeNode instance = (TreeNode) getHibernateTemplate().get(
					TreeNode.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TreeNode instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TreeNode as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
//			String queryString = "from TreeNode";
//			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findDeviceByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Device as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public TreeNode merge(TreeNode treenode) {
		log.debug("merging TreeNode instance");
		try {
			TreeNode result = (TreeNode) getHibernateTemplate().merge(
					treenode);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void save(TreeNode treenode) {
		log.debug("saving TreeNode instance");
		try {
			getHibernateTemplate().save(treenode);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
