package com.dsm.jsonfactory.depart;

import java.util.List;

import com.dsm.jsontools.JSONArray;
import com.dsm.jsontools.JSONObject;
import com.dsm.po.Depart;


public class ConDepartList2Json {

	public ConDepartList2Json()
	{
		super();
	}
	
	/**
	 * 将分页list转换成json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String ConverDepartListToPageJson(List<Depart> list, int listCount) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Depart dp : list) {
			jsonObject = new JSONObject(dp);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();

		String jsonString = "{\"totalCount\":" + listCount + ",\"rows\":"
				+ json + "}";
		return jsonString;
	}
}
