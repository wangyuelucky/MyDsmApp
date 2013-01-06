package com.dsm.dao;

import java.util.List;

import com.dsm.po.TreeNode;



public interface TreeNodeDAO {

	public abstract void save(TreeNode treenode);

	public abstract void delete(TreeNode treenode);

	public abstract TreeNode findById(java.lang.Integer id);

	public abstract List findByExample(TreeNode treenode);

	public abstract List findByProperty(String propertyName, Object value);
	
	public List findDeviceByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TreeNode merge(TreeNode treenode);

	public abstract void attachDirty(TreeNode treenode);

	public abstract void attachClean(TreeNode treenode);
}
