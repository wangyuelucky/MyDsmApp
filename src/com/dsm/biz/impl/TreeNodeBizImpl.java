package com.dsm.biz.impl;

import java.util.List;

import com.dsm.biz.TreeNodeBiz;
import com.dsm.dao.TreeNodeDAO;
import com.dsm.po.Device;
import com.dsm.po.TreeNode;

public class TreeNodeBizImpl implements TreeNodeBiz{

	TreeNodeDAO treenodedao = null;
	
	public TreeNodeBizImpl()
	{
		
	}
	public void setTreenodedao(TreeNodeDAO treenodedao) {
		this.treenodedao = treenodedao;
	}

	public List<Object> getTreeNodeById(String parentId) {
		Integer iparentId=Integer.parseInt(parentId);
		List<Object> list = treenodedao.findByProperty("parentId", iparentId);
		return list;
	}

	public List<Object> getDeviceByProject(String projectId) {
		Integer proId=Integer.parseInt(projectId);
		List<Object> list = treenodedao.findByProperty("projectId", proId);
		return list;
	}

}
