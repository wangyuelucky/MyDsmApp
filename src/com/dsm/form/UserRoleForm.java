/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.dsm.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.dsm.po.Userrole;



/**
 * MyEclipse Struts Creation date: 02-27-2009
 * 
 * XDoclet definition:
 * 
 * @struts.form name="userRoleForm"
 */
public class UserRoleForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private Userrole userRole = new Userrole();

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

	public Userrole getUserRole() {
		return userRole;
	}

	public void setUserRole(Userrole userRole) {
		this.userRole = userRole;
	}
}