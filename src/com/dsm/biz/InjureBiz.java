package com.dsm.biz;

import java.util.List;

import com.dsm.po.Injure;




public interface InjureBiz {

	/**
	 * 多条件分页模糊查询工伤档案信息
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
	public List<Injure> findInjureByPage(String name,String wktype,
			String unit,String accidate, String start, String limit);
	
	/**
	 * 多条件模糊查询工伤档案信息总记录条数
	 * 
	 * @param name
	 *            姓名
	 * @return 给定条件的总记录条数
	 */
	public int getInjureListSize(String name,String wktype,
			String unit,String accidate);
	
	/**
	 * 变更工伤档案信息
	 * 
	 * @param injure
	 * @param injureid
	 * @return
	 */
	public boolean updateInjure(Injure injure,String injureid);
	
	/**
	 * 删除工伤档案信息
	 * 
	 * @param pkid
	 *            主键id
	 * @return
	 */
	public boolean deleteInjureInfoByPkid(String pkid);
	
	/**
	 * 登记工伤档案信息
	 * 
	 * @param Injure
	 *            工伤档案信息实体
	 * @return 工伤档案信息实体
	 */
	public boolean saveInjure(Injure injure);
	
	public List<String> findByItem(String itemName);
	
	public List<Integer> countInjure();
	
	public List<Injure> checkByInstance(Injure instance);
}
