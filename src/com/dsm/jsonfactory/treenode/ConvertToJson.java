package com.dsm.jsonfactory.treenode;

import java.util.List;

import com.dsm.jsontools.JSONArray;
import com.dsm.jsontools.JSONObject;
import com.dsm.po.TreeNode;


public class ConvertToJson {

	public ConvertToJson() {
		super();
	}

	/**
	 * 将list转换成json数据
	 * @param list
	 * @return
	 */
	public static String ConverListToJson(List<TreeNode> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (TreeNode treeNode : list) {
			jsonObject = new JSONObject(treeNode);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		return json;
	}

}
