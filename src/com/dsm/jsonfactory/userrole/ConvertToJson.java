package com.dsm.jsonfactory.userrole;

import java.util.List;

import com.dsm.jsontools.JSONArray;
import com.dsm.jsontools.JSONObject;
import com.dsm.po.Userrole;


public class ConvertToJson {

	public ConvertToJson() {
		super();
	}

	/**
	 * 将分页list转换成json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String ConverListToPageJson(List<Userrole> list, int listCount) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Userrole ur : list) {
			jsonObject = new JSONObject(ur);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();

		String jsonString = "{\"totalCount\":" + listCount + ",\"rows\":"
				+ json + "}";
		return jsonString;
	}

	/**
	 * 将list转换成json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String ConverListToJson(List<Userrole> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Userrole ur : list) {
			jsonObject = new JSONObject(ur);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		return json;
	}
}
