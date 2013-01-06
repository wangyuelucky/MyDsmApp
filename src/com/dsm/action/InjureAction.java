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

import com.dsm.biz.InjureBiz;
import com.dsm.form.InjureForm;
import com.dsm.jsonfactory.injure.ConInjureList2Json;
import com.dsm.jsonfactory.stuff.ConStuffList2Json;
import com.dsm.po.Injure;



public class InjureAction extends DispatchAction{

	InjureBiz iinjureBiz = null;

	public void setIinjureBiz(InjureBiz iinjureBiz) {
		this.iinjureBiz = iinjureBiz;
	}
	
	/**
	 * 多条件分页模糊查询工伤档案信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward findInjureByPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取请求的分页参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		// 获取查询参数
		String name = request.getParameter("injureName");
		String wktype = request.getParameter("injureWktype");
		String unit = request.getParameter("injureUnit");
		String acci_date = request.getParameter("injureAcc");
		// 调用而业务(人员档案信息查询)
		List<Injure> list = this.iinjureBiz.
		     findInjureByPage(name,wktype,unit,acci_date,start, limit);
		// 调用而业务(人员档案信息查询的总记录)
		int listCount = this.iinjureBiz.getInjureListSize(name,wktype,unit,acci_date);
		// 数据格式转换
		String json = ConInjureList2Json.ConverStuffListToPageJson(list, listCount);
		// 获取流
		PrintWriter out = response.getWriter();
		// 将流打印到客户端
		out.print(json);
		// 关闭流
		out.flush();
		out.close();

		return null;
	}
	
	public ActionForward findItemByName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String itemName = request.getParameter("itemName");
		List<String> list = this.iinjureBiz.findByItem(itemName);
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
	
	/**
	 * 工伤档案变更
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward editInjure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取请求参数
		InjureForm injureForm = (InjureForm) form;
		Injure injure = injureForm.getIj();

		// 获取主键id
		String pkid = request.getParameter("injureid");
		Integer _pkid = Integer.parseInt(pkid);
		injure.setId(_pkid);          // 此处要特别注意
		// 调用业务,执行审核
		boolean result = this.iinjureBiz.updateInjure(injure, pkid);
		// 获取流
		PrintWriter out = response.getWriter();
		out.print("{success:" + result + ",errorMsg:\"操作失败,请重新操作或与管理员联系!\"}");
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 工伤档案信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward addInjure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取请求参数
		InjureForm injureForm = (InjureForm) form;
		Injure injure = injureForm.getIj();
		
		boolean result = true;
		PrintWriter out = response.getWriter();
		// 人员档案信息登记
		if(this.iinjureBiz.checkByInstance(injure).size()>0)
		{
			result = false;
			out.print("{success:" + result + ",errorMsg:\"登记失败,该档案已经存在，请不要重复提交!\"}");
		}
		else
		{
			result = this.iinjureBiz.saveInjure(injure);
			// 获取流
			
			out.print("{success:" + result + ",errorMsg:\"登记失败,请重新登记或与管理员联系!\"}");
		}
		
		out.flush();
		out.close();

		return null;
	}
	
	/**
	 * 删除工伤档案信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward deleteInjure(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取请求参数
		// 获取主键id
		String pkid = request.getParameter("injureid");
		// 调用业务,执行审核
		boolean result = this.iinjureBiz.deleteInjureInfoByPkid(pkid);
		// 获取流
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();

		return null;
	}
	
	/**
	 * 统计工伤信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward countInjure(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		 List<Integer> counts = this.iinjureBiz.countInjure();
		 request.setAttribute("countInjureList",counts);

		return null;
	}
}
