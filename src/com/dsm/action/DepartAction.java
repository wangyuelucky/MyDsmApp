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

import com.dsm.biz.DepartBiz;
import com.dsm.jsonfactory.depart.ConDepartList2Json;
import com.dsm.po.Depart;


public class DepartAction extends DispatchAction{

	DepartBiz idepartBiz = null;

	public void setIdepartBiz(DepartBiz idepartBiz) {
		this.idepartBiz = idepartBiz;
	}
	
	public ActionForward findDepartByPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取请求的分页参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		// 获取查询参数
		String name = null;
		// 调用而业务(人员档案信息查询)
		List<Depart> list = this.idepartBiz.findDepartByPage(name,start, limit);
		// 调用而业务(人员档案信息查询的总记录)
		int listCount = this.idepartBiz.getDepartListSize(name);
		// 数据格式转换
		String json = ConDepartList2Json.ConverDepartListToPageJson(list, listCount);
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
	
	public ActionForward insertOrUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String dpId = request.getParameter("id");
		String dpIu = request.getParameter("iu");
		String dpName = request.getParameter("name");
		String dpComment = request.getParameter("comment");
		
		Depart depart = new Depart();
		depart.setDepart_name(dpName);
		depart.setComment(dpComment);
		
		if("1".equals(dpIu))
		{
			boolean result = true;
			if(this.idepartBiz.findDepartByName("depart_name", 
					depart.getDepart_name()).size()>0)
			{
				result = false;
			}
			else
			{
				result = this.idepartBiz.saveDepartFile(depart);
			}
			
			// 获取流
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		}
		else
		{
			boolean result = this.idepartBiz.updateDepartFile(depart, dpId);
			// 获取流
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		}

		return null;
	}
	
	public ActionForward deleteDepart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取主键id
		String pkid = request.getParameter("departid");
		// 调用业务,执行审核
		boolean result = this.idepartBiz.deleteDepartInfoByPkid(pkid);
		// 获取流
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();

		return null;
	}
}
