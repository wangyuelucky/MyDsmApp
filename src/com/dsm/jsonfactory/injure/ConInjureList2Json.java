package com.dsm.jsonfactory.injure;


import java.util.List;

import com.dsm.jsontools.JSONArray;
import com.dsm.jsontools.JSONObject;
import com.dsm.po.Injure;


public class ConInjureList2Json {

	public ConInjureList2Json(){
		super();
	}
	
	/**
	 * 将分页list转换成json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String ConverStuffListToPageJson(List<Injure> list, int listCount) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Injure ij : list) {
			String acci_date = ij.getAcci_date();
			String apply_date = ij.getApply_date();
			String begin = ij.getBegin();
			try {
				acci_date = acci_date.substring(0, 10);
				apply_date = apply_date.substring(0, 10);
				begin = begin.substring(0, 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ij.setAcci_date(acci_date);
			ij.setApply_date(apply_date);
			ij.setBegin(begin);
			jsonObject = new JSONObject(ij);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();

		String jsonString = "{\"totalCount\":" + listCount + ",\"rows\":"
				+ json + "}";
		return jsonString;
	}
}
