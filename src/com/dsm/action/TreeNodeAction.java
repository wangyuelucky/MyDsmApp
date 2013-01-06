package com.dsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.dsm.biz.TreeNodeBiz;
import com.dsm.jdbc.conn.DeviceProjectDao;
import com.dsm.po.Device;
import com.dsm.po.TreeNode;

public class TreeNodeAction extends DispatchAction{
	
	private TreeNodeBiz treenodeBiz = null;

	public void setTreenodeBiz(TreeNodeBiz treenodeBiz) {
		this.treenodeBiz = treenodeBiz;
	}
	
	/**
	 * 根据父节点显示子节点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showNode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			//获取流
			PrintWriter out = response.getWriter();
			//获取请求参数
			String node = request.getParameter("node");
			DeviceProjectDao dpd = new DeviceProjectDao();
			//调用业务
			List<TreeNode> list = new ArrayList<TreeNode>();
			Map<String,TreeNode> map = new HashMap<String,TreeNode>();
			
			list = dpd.queryProject("1");
			list = createTreeJson(dpd,list, map);

			// 一次性加载所有节点
			JSONArray ja = JSONArray.fromObject(list); 
			String json = ja.toString();
			System.out.println("TreeNodeAction" + json);
			//将流打到客户端
			out.print(json);
			//清空缓存
			out.flush();
			//关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<TreeNode> createTreeJson(DeviceProjectDao dao,List<TreeNode> list,Map<String,TreeNode> map){
		for(TreeNode tn :list){
			// 防止重复加载
			if(!map.containsKey(tn.getId().toString())){
				map.put(tn.getId().toString(), tn);
				// 子项目
				List<TreeNode> projects = dao.queryProject(tn.getId().toString());
				// 设备
				List<Device> devices = dao.queryDevice(tn.getId().toString());
				// 合并 子项目 和 设备
				List children = new ArrayList();
				children.addAll(projects);
				children.addAll(devices);
				
				if(children.size()>0){
					tn.setLeaf(false);
				}
				tn.setChildren(children);
				if(projects.size()>0){
					createTreeJson(dao,projects, map);
				}
			}
						
		}
		return list;
	}
	
	public ActionForward saveOrUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			//获取流
			PrintWriter out = response.getWriter();
			//获取请求参数
			String parms = request.getParameter("nodeInfo");
			String[] node = parms.split(",");
			DeviceProjectDao dpd = new DeviceProjectDao();
			boolean result = true;
			
			if(node[0].indexOf('y')==-1){// 修改项目名称
				String proId = node[0].split(":")[1];
				String proName = node[1].split(":")[1];
				TreeNode editNode = new TreeNode();
				editNode.setId(Integer.parseInt(proId));
				editNode.setText(proName);
				result = dpd.editProject(editNode);
			}else{ // 新建子项目
				String parentid = node[2].split(":")[1];
				String proName = node[1].split(":")[1];
				TreeNode tree = new TreeNode();
				tree.setIconCls("wy-projectLeafIcon");
				tree.setLeaf(true);
				tree.setLinkUrl("");
				tree.setParentId(Integer.parseInt(parentid));
				tree.setText(proName);
				result = dpd.newProject(tree);
			}
			
			//将流打到客户端
			out.print(result);
			//清空缓存
			out.flush();
			//关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ActionForward deletePro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			//获取流
			PrintWriter out = response.getWriter();
			//获取请求参数
			String parms = request.getParameter("nodeInfo");
			String[] node = parms.split(",");
			DeviceProjectDao dpd = new DeviceProjectDao();
			boolean result = true;
			
			String proId = node[0].split(":")[1];
			String parentId = node[1].split(":")[1];

			result = dpd.delProject(Integer.parseInt(proId), Integer.parseInt(parentId));

			//将流打到客户端
			out.print(result);
			//清空缓存
			out.flush();
			//关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
