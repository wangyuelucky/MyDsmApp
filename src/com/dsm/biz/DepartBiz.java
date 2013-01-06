package com.dsm.biz;

import java.util.List;

import com.dsm.po.Depart;



public interface DepartBiz {


	public List<Depart> findDepartByPage(String name, String start, String limit);
	

	public int getDepartListSize(String name);
	
	public boolean updateDepartFile(Depart depart, String pkid);
	
	public boolean saveDepartFile(Depart depart);
	
	public List<Depart> findDepartByName(String propertyName, String value);
	
	public boolean deleteDepartInfoByPkid(String pkid);
}
