package com.dsm.jdbc.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dsm.po.Device;
import com.dsm.po.TreeNode;

public class DeviceProjectDao {
		
		private final static String findProjectSQL = "select * from primarykeycn where parent_id=?";
		
		private final static String findDeviceSQL = "select * from device where projectid=?";
		
		private final static String newProjectSQL = "insert into primarykeycn(text,parent_id,leaf,link_url,iconCls) values(?,?,?,?,?)";
		
		private final static String editProjectSQL = "update primarykeycn set text=? where id=?";
				
		private final static String delProjectByID = "delete from primarykeycn where id=?";
		
		private final static String updateDeviceProject = "update device set projectid=? where projectid=?";
		//数据库连接对象
		private Connection con = null;
		//用于发送SQL指令的JDBC api中的一个类
		//注意:发送的是静态的SQL
		private Statement st = null;
		//可以发送预编译SQL(带?的SQL)指令的类
		private PreparedStatement pstmt = null;
		//返回信息的结果集
		private ResultSet rs = null;
			
		public List<TreeNode> queryProject(String parentid)
		{
			con = ConnectionFactory.getConnection();
			List<TreeNode> list = new ArrayList<TreeNode>();
			try {
				pstmt = con.prepareStatement(findProjectSQL);
				pstmt.setInt(1, Integer.parseInt(parentid));
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					TreeNode tn = new TreeNode();
					tn.setId(rs.getInt(1));
					tn.setText(rs.getString(2));
					tn.setParentId(rs.getInt(3));
					tn.setLeaf(rs.getBoolean(4));
					tn.setLinkUrl(rs.getString(5));
					tn.setIconCls(rs.getString(6));
					tn.setDraggable(false);
					tn.setpOd("p");
					list.add(tn);
				}
			} catch (Exception e) {
				e.getStackTrace();
			}finally{
				if(st != null || con !=null)
				{
					try {
						st.close();
						con.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			return list;
		}
		
		/**
		 * 
		 * @param p
		 * @return
		 */
		public List<Device> queryDevice(String project)
		{
			con = ConnectionFactory.getConnection();
			List<Device> list = new ArrayList<Device>();
			try {
				pstmt = con.prepareStatement(findDeviceSQL);
				pstmt.setInt(1, Integer.parseInt(project));
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Device de = new Device();
					de.setId(rs.getInt(1));
					de.setText(rs.getString(2));
					de.setState(rs.getInt(3));
					de.setProjectId(rs.getInt(4));
					de.setLinkUrl(rs.getString(5));
					de.setIconCls(rs.getString(6));
					de.setLeaf(true);
					de.setIsTarget(false);
					de.setpOd("d");
					String stateTip = rs.getInt(3)==0 ? "<span style='color: #226655;'>未审核</span>" : 
							"<span style='color: #ff0000;'>拒绝</span>";
					de.setQtip(rs.getString(2)+" "+stateTip);
					list.add(de);
				}
			} catch (Exception e) {
				e.getStackTrace();
			}finally{
				if(st != null || con !=null)
				{
					try {
						st.close();
						con.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			return list;
		}
		
		public boolean newProject(TreeNode node)
		{
			con = ConnectionFactory.getConnection();
			boolean result = true;
			try {
				pstmt = con.prepareStatement(newProjectSQL);
				pstmt.setString(1, node.getText());
				pstmt.setInt(2, node.getParentId());
				pstmt.setBoolean(3, node.getLeaf());
				pstmt.setString(4, node.getLinkUrl());
				pstmt.setString(5, node.getIconCls());
				pstmt.executeUpdate();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			}finally{
				if(st != null || con !=null)
				{
					try {
						st.close();
						con.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			return result;
		}
		
		public boolean delProject(int nodeID,int parentID)
		{
			con = ConnectionFactory.getConnection();
			boolean result = true;
			try {
				deleteChildren(nodeID, parentID);
			} catch (SQLException e) {
				result = false;
				e.printStackTrace();
			}
			return result;
		}
		
		public void deleteChildren(int nodeID,int ancestryID) throws SQLException{
			List<TreeNode> list = queryProject(String.valueOf(nodeID));
			// 没有子项目
			if(list.size() >= 1){
				for (TreeNode treeNode : list) {
					// 删除所有子项目
					deleteChildren(treeNode.getId(),ancestryID);	
					// 删除项目
					deleteProjectByID(nodeID, ancestryID);
				}
			}else{
				// 删除项目
				deleteProjectByID(nodeID, ancestryID);
			}
		}
		
		public void deleteProjectByID(int nodeID,int ancestryID) throws SQLException
		{
			con = ConnectionFactory.getConnection();

			// 把设备放置到，祖宗项目下
			pstmt = con.prepareStatement(updateDeviceProject);
			pstmt.setInt(1, ancestryID);
			pstmt.setInt(2, nodeID);
			pstmt.executeUpdate();
			// 删除该项目
			pstmt = con.prepareStatement(delProjectByID);
			pstmt.setInt(1, nodeID);
			pstmt.executeUpdate();

			if(con !=null){
				con.close();
			}
			
		}
		
		public boolean editProject(TreeNode node)
		{
			con = ConnectionFactory.getConnection();
			boolean result = true;
			try {
				pstmt = con.prepareStatement(editProjectSQL);
				pstmt.setString(1, node.getText());
				pstmt.setInt(2, node.getId());
				pstmt.executeUpdate();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			}finally{
				if(st != null || con !=null)
				{
					try {
						st.close();
						con.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			return result;
		}
		
		public static void main(String[] args) {
			DeviceProjectDao dpd = new DeviceProjectDao();
			List projects = dpd.queryProject("1");
			System.out.println(projects.size());
			
			List devices = dpd.queryDevice("20");
			System.out.println(devices.size());
		}
}
