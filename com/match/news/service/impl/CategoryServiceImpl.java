package com.match.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.LftrgtDao;
import com.match.dao.impl.LftrgtDaoImpl;
import com.match.news.mapper.CategoryMapper;
import com.match.news.model.Category;
import com.match.news.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private  CategoryMapper  categorymapper;
	
	@Autowired
	private  LftrgtDao  lftrtdao;
	
	@Override
	public boolean addCategory(Category category) throws Exception {
		
		//添加分类
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		category.setMtime(sdf.parse(sdf.format(new Date())));
		
		Map<String,Object>  map = categorymapper.findMaxOrder();
		if(map==null)
		{
			category.setRanking(1);
		}
		else
		{
			category.setRanking(Integer.parseInt(map.get("ranking").toString())+1);
		}
		 int i = categorymapper.insertSelective(category);
		 if(i>0)
		 {
			 return true;
		 }
		 return false;
//		String[] s = {};
		//插入穿线树
//		LftrgtDaoImpl ss = new LftrgtDaoImpl();
//		ss.insert("Category", category.getId(), category.getPid(), 1,s);
//		return   lftrtdao.insert("Category", category.getId(), category.getPid(), 1,s);
	}

	@Override
	public boolean deleteCategory(long id) throws Exception {
		// TODO Auto-generated method stub
//		String[] s = {};
//		lftrtdao.delete("Category", id, s);
		int i = categorymapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		int i = categorymapper.updateByPrimaryKeySelective(category);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public Map<String, Object> findCategoryById(long id) throws Exception {
		// TODO Auto-generated method stub
		return categorymapper.findCategoryById(id);
		 
	}

	@Override
	public List<Map<String, Object>> findCategoryList() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  listmap = categorymapper.findCategoryList();
//		listmap = getMenu(listmap, 0);
		return listmap;
	}
	
	
	  public    List<Map<String, Object>>    getMenu(List<Map<String, Object>> aa, long i)    throws    Exception
	  {
		
		    List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : aa) {
				if(i==Integer.parseInt(map.get("pid").toString())){
					map.put("id", map.get("id"));
					map.put("name", map.get("name"));
					List<Map<String,Object>> all2 = getMenu(aa,Integer.parseInt(map.get("id").toString()));
					if(all2.size()>0){
						map.put("children", all2);
					}
					all.add(map);
				}
			}
			return all;
	  }

}
