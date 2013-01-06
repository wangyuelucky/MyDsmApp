package com.dsm.jsonfactory.stuff;

import java.util.Iterator;
import java.util.List;

import com.dsm.jsontools.JSONArray;
import com.dsm.jsontools.JSONObject;
import com.dsm.po.Stuff;



public class ConStuffList2Json {

	public ConStuffList2Json() {
		super();
	}
	
	/**
	 * 将分页list转换成json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String ConverStuffListToPageJson(List<Stuff> list, int listCount) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Stuff st : list) {
			String birth = st.getBirth();
			String begin = st.getBegin();
			String gettime = st.getGettime();
			String stime = st.getStime();
			String etime = st.getEtime();
			try {
				birth = birth.substring(0, 10);
				System.out.println(birth.toString());
				begin = begin.substring(0, 10);
				gettime = gettime.substring(0, 10);
				stime = stime.substring(0, 10);
				etime = etime.substring(0, 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			st.setBirth(birth);
			st.setBegin(begin);
			st.setGettime(gettime);
			st.setStime(stime);
			st.setEtime(etime);
			jsonObject = new JSONObject(st);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();

		String jsonString = "{\"totalCount\":" + listCount + ",\"rows\":"
				+ json + "}";
		return jsonString;
	}
	
	public static String ConverStringListToPageJson(List<String> list,String itemName) {
		 String json = "";
		 Iterator<String> it = list.iterator();
		 while(it.hasNext()){
		  if(json.equals("")){
		   json = "[{\"cfgValue\":\"" + it.next() + "\",\"cfgItem\":\"" + itemName + "\"}";
		  }else{
		   json = json+","+
		   "{\"cfgValue\":\"" + it.next() + "\",\"cfgItem\":\"" + itemName + "\"}";
		  }
	    }
		 json += "]";
		 return json;
	}
}
