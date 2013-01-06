package com.dsm.po;

public class Depart implements java.io.Serializable{

	private int id;
	private String depart_name;  // 部门名称
	private String comment;      // 备注
	
	public Depart(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
