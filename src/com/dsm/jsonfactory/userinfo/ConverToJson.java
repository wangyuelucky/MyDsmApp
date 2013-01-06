package com.dsm.jsonfactory.userinfo;

import java.util.List;

import com.dsm.jsontools.JSONArray;
import com.dsm.jsontools.JSONObject;
import com.dsm.po.Userinfo;


public class ConverToJson {

	public ConverToJson() {
		super();
	}

	/**
	 * 将list转换成json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String ConverListToJson(List<Userinfo> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Userinfo ui : list) {
			jsonObject = new JSONObject(ui);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		return json;
	}

	/**
	 * 将list转换成分页json数据
	 * 
	 * @param list
	 *            集合
	 * @param count
	 *            总记录的条数
	 * @return
	 */
	public static String ConverListToPageJson(List<Userinfo> list, int count) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Userinfo ui : list) {
			jsonObject = new JSONObject(ui);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		String jsonString = "{\"totalCount\":" + count + ",\"rows\":" + json
				+ "}";
		return jsonString;
	}
}
