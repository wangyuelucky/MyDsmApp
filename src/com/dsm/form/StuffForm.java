package com.dsm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.dsm.po.Stuff;


public class StuffForm extends ActionForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stuff st = new Stuff();

	
	public Stuff getSt() {
		return st;
	}

	public void setSt(Stuff st) {
		this.st = st;
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
