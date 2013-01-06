package com.dsm.po;


/**
 * Userrole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Userrole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer urId;
	private String urName;
	private String urMemo;


	// Constructors

	/** default constructor */
	public Userrole() {
	}

	// Property accessors

	public Integer getUrId() {
		return this.urId;
	}

	public void setUrId(Integer urId) {
		this.urId = urId;
	}

	public String getUrName() {
		return this.urName;
	}

	public void setUrName(String urName) {
		this.urName = urName;
	}

	public String getUrMemo() {
		return this.urMemo;
	}

	public void setUrMemo(String urMemo) {
		this.urMemo = urMemo;
	}

}