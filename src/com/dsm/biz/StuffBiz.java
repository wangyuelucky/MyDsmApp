package com.dsm.biz;

import java.util.List;

import com.dsm.po.Stuff;


public interface StuffBiz {

	/**
	 * 多条件分页模糊查询员工信息
	 * 
	 * @param name
	 *            姓名
	 * @param addDate
	 *            添加日期
	 * @param start
	 *            索引
	 * @param limit
	 *            页大小
	 * @return 当前页的所有数据
	 */
	public List<Stuff> findStuffByPage(String name,String sex,String iden,
			String dep,String wk,String edu,String cardid,String com, 
			String start, String limit);
	
	/**
	 * 多条件模糊查询员工信息总记录条数
	 * 
	 * @param name
	 *            姓名
	 * @return 给定条件的总记录条数
	 */
	public int getStuffListSize(String name,String sex,String iden,
			String dep,String wk,String edu,String cardid,String com);
	
	/**
	 * 变更员工信息
	 * 
	 * @param stuff
	 * @param stuffid
	 * @return
	 */
	public boolean updateStuff(Stuff stuff,String stuffid);
	
	/**
	 * 删除员工信息
	 * 
	 * @param pkid
	 *            主键id
	 * @return
	 */
	public boolean deleteStuffInfoByPkid(String pkid);
	
	/**
	 * 登记员工档案信息
	 * 
	 * @param Stuff
	 *            人员信息实体
	 * @return 人员信息实体
	 */
	public boolean saveStuff(Stuff stuff);
	/**
	 * 根据列名查询对应的记录
	 * @param itemName
	 * @return
	 */
	public List<String> findByItem(String itemName);
	
	public List<Stuff> checkByIdent(String ident);
}
