package com.match.dao;

/** 
 * @author mengly 
 * @version 创建时间：2016年7月23日 下午4:24:28 
 * 类说明 
 */

public interface LftrgtDao {
	public boolean insert(String table,long id,long pid,long ordering,String[] cons);
	public  boolean update(String table,long id,long pid,long ordering,String[] cons);
	public  boolean delete(String table,long id,String[] cons);
}
