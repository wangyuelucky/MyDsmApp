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

import com.dsm.biz.UserRoleBiz;
import com.dsm.form.UserRoleForm;
import com.dsm.jsonfactory.userrole.ConvertToJson;
import com.dsm.po.Userrole;


public class UserRoleAction extends DispatchAction{

    //	 用户角色业务接口
	UserRoleBiz iurb = null;

	public void setIurb(UserRoleBiz iurb) {
		this.iurb = iurb;
	}
	
	/**
	 * 分页显示所有用户角色信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showUserRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 获取请求参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		// 调用业务
		List<Userrole> list = iurb.showAllUserRolesByPage(start, limit);
		// 将获取的信息转换成json数据格式
		String json = ConvertToJson.ConverListToPageJson(list, iurb
				.getUserRolesCount());
		// 设置相应内容类型
		response.setContentType("text/html;charset=utf-8");
		try {
			// 获取流
			PrintWriter out = response.getWriter();
			// 将流打到客户端
			out.print(json);
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示所有用户角色信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showAllUserRoles(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// 调用业务
		List<Userrole> list = iurb.showAllUserRoles();
		// 将获取的信息转换成json数据格式
		String json = ConvertToJson.ConverListToJson(list);
		// 设置相应内容类型
		response.setContentType("text/html;charset=utf-8");
		try {
			// 获取流
			PrintWriter out = response.getWriter();
			// 将流打到客户端
			out.print(json);
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加用户角色信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 获取请求参数
		UserRoleForm urf = (UserRoleForm) form;
		Userrole urInstance = urf.getUserRole();

		// 调用业务
		boolean result = this.iurb.addUserRole(urInstance);
		// 设置文本流头信息
		response.setContentType("text/html;charset=utf-8");

		try {
			// 获取流
			PrintWriter out = response.getWriter();
			// 将流打到客户端
			out.print("{success:" + result + ",errorMsg:\"添加失败,当前角色可能已存在!\"}");
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑所有用户角色信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward editUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 获取请求参数
		UserRoleForm urf = (UserRoleForm) form;
		Userrole urInstance = urf.getUserRole();
		String id = request.getParameter("id");
		urInstance.setUrId(Integer.parseInt(id));

		// 调用业务
		boolean result = this.iurb.updateUserRole(urInstance);
		// 设置文本流头信息
		response.setContentType("text/html;charset=utf-8");

		try {
			// 获取流
			PrintWriter out = response.getWriter();
			// 将流打到客户端
			out.print("{success:" + result + ",errorMsg:\"编辑失败,当前角色可能已存在!\"}");
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
