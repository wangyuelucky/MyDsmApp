/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.dsm.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.dsm.po.Userinfo;



/**
 * MyEclipse Struts Creation date: 02-26-2009
 * 
 * XDoclet definition:
 * 
 * @struts.form name="userInfoForm"
 */
public class UserInfoForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private Userinfo userInfo = new Userinfo();

	public Userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Userinfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
}