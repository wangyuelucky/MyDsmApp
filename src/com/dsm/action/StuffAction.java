package com.dsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.dsm.biz.StuffBiz;
import com.dsm.form.StuffForm;
import com.dsm.jsonfactory.stuff.ConStuffList2Json;
import com.dsm.po.Stuff;




public class StuffAction extends DispatchAction{

	StuffBiz istuffBiz = null;

	public void setIstuffBiz(StuffBiz istuffBiz) {
		this.istuffBiz = istuffBiz;
	}
	
	/**
	 * 多条件分页模糊查询人员档案信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward findStuffByPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 获取请求的分页参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		// 获取查询参数
		String name = request.getParameter("stuffName");
		String sex = request.getParameter("stuffSex");
		String iden = request.getParameter("stuffIden");
		String dep = request.getParameter("stuffDep");
		String wk = request.getParameter("stuffWk");
		String edu = request.getParameter("stuffEdu");
		String cardid = request.getParameter("stuffCard");
		String comm = request.getParameter("stuffCom");
		// 调用而业务(人员档案信息查询)
		List<Stuff> list = this.istuffBiz.
		    findStuffByPage(name, sex, iden, dep, wk, edu, cardid, comm, start, limit);
		// 调用而业务(人员档案信息查询的总记录)
		int listCount = this.istuffBiz.getStuffListSize(name, sex, iden, dep, wk, edu, cardid, comm);
		// 数据格式转换
		String json = ConStuffList2Json.ConverStuffListToPageJson(list, listCount);
		// 获取流
		PrintWriter out = response.getWriter();
		// 将流打印到客户端
		out.print(json);
		// 关闭流
		out.flush();
		out.close();

		return null;
	}
	
	/**
	 * 员工信息变更
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward editStuff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取请求参数
		StuffForm stuffForm = (StuffForm) form;
		Stuff stuff = stuffForm.getSt();

		// 获取主键id
		String pkid = request.getParameter("stuffid");
		Integer _pkid = Integer.parseInt(pkid);
		stuff.setId(_pkid);          // 此处要特别注意
		// 调用业务,执行审核
		boolean result = this.istuffBiz.updateStuff(stuff,pkid);
		// 获取流
		PrintWriter out = response.getWriter();
		out.print("{success:" + result + ",errorMsg:\"操作失败,请重新操作或与管理员联系!\"}");
		out.flush();
		out.close();

		return null;
	}
	
	/**
	 * 删除员工信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward deleteStuff(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取请求参数
		// 获取主键id
		String pkid = request.getParameter("stuffid");
		// 调用业务,执行审核
		boolean result = this.istuffBiz.deleteStuffInfoByPkid(pkid);
		// 获取流
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();

		return null;
	}
	
	/**
	 * 人员档案信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward addStuff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取请求参数
		StuffForm stuffForm = (StuffForm) form;
		Stuff stuff = stuffForm.getSt();
		
		// 注意： 不能写成 if("请选择开始工作日期".equals(stuff.getBegin())) stuff.setBegin("");
		if("请选择开始工作日期".equals(stuff.getBegin())) stuff.setBegin(null);
		if("请选择出生日期".equals(stuff.getBirth())) stuff.setBirth(null);
		if("请选择领证日期".equals(stuff.getGettime())) stuff.setGettime(null);
		if("请选择有效期".equals(stuff.getStime())) stuff.setStime(null);
		if("请选择有效期".equals(stuff.getEtime())) stuff.setEtime(null);
		
		boolean result = true;
		if(this.istuffBiz.checkByIdent(stuff.getIdentity()).size()>0)
			result = false;
		else {
			// 人员档案信息登记
			result = this.istuffBiz.saveStuff(stuff);
		}
		
		// 获取流
		PrintWriter out = response.getWriter();
		out.print("{success:" + result + ",errorMsg:\"登记失败,可能该记录已经存在<br/>请重新登记或与管理员联系!\"}");
		out.flush();
		out.close();

		return null;
	}
	
	public ActionForward findItemByName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String itemName = request.getParameter("itemName");
		List<String> list = this.istuffBiz.findByItem(itemName);
		String json = ConStuffList2Json.ConverStringListToPageJson(list,itemName);
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 将流打印到客户端
		out.print(json);
		// 关闭流
		out.flush();
		out.close();

		return null;
	}
}
