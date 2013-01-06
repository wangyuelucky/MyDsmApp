package com.dsm.po;

public class Device {

	private Integer id;
	private String text;
	private int state;
	private String pOd; // 标示是设备还是项目，此字段不写入数据库
	private String linkUrl;
	private Integer projectId;
	private String qtip;  // 信息提示文本,此字段不写入数据库
	private String iconCls;
	private boolean leaf; // 设备必须是叶子节点，此字段不写入数据库
	private boolean isTarget; // 设备不能作为拖拽的目的节点，此字段不写入数据库
		
	
	public String getpOd() {
		return pOd;
	}
	public void setpOd(String pOd) {
		this.pOd = pOd;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public boolean isIsTarget() {
		return isTarget;
	}
	public void setIsTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
