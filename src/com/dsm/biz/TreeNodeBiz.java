package com.dsm.biz;

import java.util.List;

import com.dsm.po.Device;
import com.dsm.po.TreeNode;


public interface TreeNodeBiz {

	/**
	 * 获取父节点的所有子节点
	 * 
	 * @param parentId 父节点
	 * @return 返回父节点的所有子节点
	 */
	public abstract List<Object> getTreeNodeById(String parentId);
	
	public abstract List<Object> getDeviceByProject(String projectId);
}
