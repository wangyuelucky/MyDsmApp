package com.dsm.po;

import java.util.List;

public class TreeNode implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String text;
	private String pOd; // 标示是设备还是项目，此字段不写入数据库
	private Boolean leaf;
	private boolean draggable; // 项目不能被拖拽，此字段不写入数据库
	private String linkUrl;
	private Integer parentId;
	private String iconCls;
	private List<?> children;


	public TreeNode()
	{
		
	}
	
	public String getpOd() {
		return pOd;
	}
	public void setpOd(String pOd) {
		this.pOd = pOd;
	}
	public boolean isDraggable() {
		return draggable;
	}
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}
	public List<?> getChildren() {
		return children;
	}
	public void setChildren(List<?> children) {
		this.children = children;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getLinkUrl() {
		return this.linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
