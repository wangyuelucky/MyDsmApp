package com.dsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.dsm.biz.UserInfoBiz;
import com.dsm.form.UserInfoForm;
import com.dsm.jsonfactory.userinfo.ConverToJson;
import com.dsm.po.Userinfo;




public class UserInfoAction extends DispatchAction{

     //	 用户信息业务接口
	private UserInfoBiz iuib = null;

	public void setIuib(UserInfoBiz iuib) {
		this.iuib = iuib;
	}
	
	/**
	 * 显示用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward showUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 调用业务
		List<Userinfo> list = iuib.showUserInfo();
		// 转换数据格式
		String json = ConverToJson.ConverListToJson(list);
		// 设置响应内容格式
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();

		return null;
	}

	/**
	 * 分页显示用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward showUserInfoByPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取请求参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		// 调用业务
		List<Userinfo> list = iuib.showUserInfo(start, limit);
		// 转换数据格式
		String json = ConverToJson.ConverListToPageJson(list, this.iuib
				.getCount());
		//System.out.println("UserInfoAction showInfoByPage" + json);
		// 设置响应内容格式
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();

		return null;
	}

	/**
	 * 注册用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward addUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取提交的值
		UserInfoForm userInfoForm = (UserInfoForm) form;
		Userinfo userInfo = userInfoForm.getUserInfo();
		// 调用业务
		boolean result = this.iuib.addUserInfo(userInfo);
		// 设置响应内容格式
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print("{success:" + result + ",errorMsg:\"注册失败,该账号可能已存在!\"}");
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();

		return null;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward editUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取提交的值
		UserInfoForm userInfoForm = (UserInfoForm) form;
		String id = request.getParameter("id");
		Userinfo userInfo = userInfoForm.getUserInfo();
		userInfo.setUserId(Integer.parseInt(id));
		// 调用业务
		boolean result = this.iuib.updateUserInfo(userInfo);
		// 设置响应内容格式
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print("{success:" + result + ",errorMsg:\"修改失败,请重新修改或与管理员联系!\"}");
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();

		return null;
	}

	/**
	 * 用户登陆
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		//标准验证码
//		String standardValidCode=session.getAttribute("validateCode").toString();
//		String standardValidCode = request.getParameter("validCode");
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		//实际验证码
//		String validCode = request.getParameter("validCode");
		Userinfo userInfo = this.iuib.checkLogin(userName);
		
		//错误信息集合
		ActionMessages errors=new ActionErrors();
		
		if (null!=userInfo) {
			if(userPass.equals(userInfo.getUserPass())){
			}else{
				errors.add("pwdError",new ActionMessage("com.dsm.login.userPass"));
			}
			request.getSession().setAttribute("userInfo", userInfo);
		}else{
			errors.add("userNameError",new ActionMessage("com.dsm.login.userName"));
		}
		
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return new ActionForward("/login.jsp",false);
		}
		
		return new ActionForward("/index.jsp",true);
	}
	
	public ActionForward showUserInfoByAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String userName = request.getParameter("userAccount");
		String userPass = request.getParameter("oPass");
		String newPass = request.getParameter("userPass");
		
		Userinfo userInfo = this.iuib.checkLogin(userName);
		if (null!=userInfo) {
			if(userPass.equals(userInfo.getUserPass())){
				userInfo.setUserPass(newPass);
				this.iuib.updateUserInfo(userInfo);
				response.setContentType("text/html;charset=utf-8");
				boolean result = true;
				// 获取流
				PrintWriter out = response.getWriter();
				// 将数据以json格式打到客户端
				out.print("{success:" + result + ",goodMsg:\"密码修改成功!\"}");
				// 清空缓存
				out.flush();
				// 关闭流
				out.close();
			}else{
				response.setContentType("text/html;charset=utf-8");
				boolean result = false;
				// 获取流
				PrintWriter out = response.getWriter();
				// 将数据以json格式打到客户端
				out.print("{success:" + result + ",errorMsg:\"密码输入错误!\"}");
				// 清空缓存
				out.flush();
				// 关闭流
				out.close();
			}
		}else{
			response.setContentType("text/html;charset=utf-8");
			boolean result = false;
			// 获取流
			PrintWriter out = response.getWriter();
			// 将数据以json格式打到客户端
			out.print("{success:" + result + ",errorMsg:\"该用户不存在，请重新输入!\"}");
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();
		}
		//String json = "\"rows\":[{\"userAccount\":\"" + userName + "\",\"userPass\":\"" + userPass + "\"}]";
		//System.out.println("UserInfoAction :" + json);
		//PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		//out.print(json);
		// 清空缓存
		//out.flush();
		// 关闭流
		//out.close();

		return null;
	}

	/**
	 * 修改用户权限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward editUserInfoRole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取提交的值
		String userId = request.getParameter("userId");
		String roleId = request.getParameter("myUserRoleId");
		// 调用业务
		boolean result = this.iuib.updateUserInfoRole(Integer.parseInt(userId),
				Integer.parseInt(roleId));
		// 设置响应内容格式
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print("{success:" + result + ",errorMsg:\"分配失败,请重新修改或与管理员联系!\"}");
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();
		return null;
	}
	
	public ActionForward deleteUserInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取请求参数
		// 获取主键id
		String pkid = request.getParameter("userid");
		// 调用业务,执行审核
		boolean result = this.iuib.deleteUserById(pkid);
		// 获取流
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();

		return null;
	}

}
