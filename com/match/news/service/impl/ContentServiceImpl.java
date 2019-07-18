package com.match.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.news.mapper.ContentFileMapper;
import com.match.news.mapper.ContentMapper;
import com.match.news.model.Content;
import com.match.news.model.ContentFile;
import com.match.news.service.ContentFileService;
import com.match.news.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private  ContentMapper  contentMapper;
	
	@Autowired
	private  ContentFileService  ContentFileService;
	
	@Autowired
	private  ContentFileMapper  ContentFileMapper;
	
	@Override
	public boolean addContent(Content content,ContentFile contentFile) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		content.setMtime(sdf.parse(sdf.format(new Date())));
		content.setViews((long)0);
		content.setStatus(1);
		int i  =  contentMapper.insertSelective(content);
		contentFile.setContentid(content.getId());
		contentFile.setMtime(UserChangLiang.mtime());
		int k = ContentFileMapper.insertSelective(contentFile);
		if(i>0 && k>0)
		{
			return  true;
		}
		return false;
		
	}

	@Override
	public boolean deleteContent(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = contentMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public boolean updateContent(Content content,ContentFile contentFile) throws Exception {
		// TODO Auto-generated method stub
		int i = contentMapper.updateByPrimaryKeySelective(content);
		ContentFileMapper.deleteByContentId(content.getId());
		contentFile.setContentid(content.getId());
		contentFile.setMtime(UserChangLiang.mtime());
		int k = ContentFileMapper.insertSelective(contentFile);
		if(i>0 && k>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public PageResult<Map<String, Object>> findContentPage(Content content, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		try {
			int count=contentMapper.count(content);
			prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			prr.setRowCount(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Map<String,Object>> ml = contentMapper.findContentPage(content.getTitle(), pageModel.getPage(), pageModel.getPagesize());
		
		prr.setItems(ml);
		return prr;
	}

	@Override
	public Map<String, Object> findContentByID(long id) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list = ContentFileService.findContentFileList(id);
		Map<String,Object>  map =  contentMapper.findContentByID(id);
		updateContentViews(id);
		if(map!=null && map.size()>0)
		{
			map.put("file", list);
		}
		return  map;
	}

	@Override
	public boolean updateContentViews(long id) throws Exception {
		// TODO Auto-generated method stub
		Content content = new Content();
		content.setId(id);
		Map<String,Object> map = contentMapper.findMaxViewsById(id);
		content.setViews((long)Integer.parseInt(map.get("views").toString())+1);
		int i = contentMapper.updateByPrimaryKeySelective(content);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public Map<String, Object> findContentByIDWeb(long id) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = contentMapper.findfindContentByIDWeb(id);
		return map;
	}

	@Override
	public PageResult<Map<String, Object>> findContentPageWeb(PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		try {
			int count=contentMapper.countWeb();
			prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			prr.setRowCount(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Map<String,Object>> ml = contentMapper.findContentPageWeb(pageModel.getPage(), pageModel.getPagesize());
		List<String> s = new ArrayList<String>();
		for (Map<String, Object> map : ml) {
			s.add(map.get("id").toString());
		}
		
		List<Map<String,Object>> mll = ContentFileMapper.findContentFile(s);
		
		for (Map<String, Object> map : ml) {
			for (Map<String, Object> map2 : mll) {
				if(map.get("id").toString().equals(map2.get("contentID").toString()))
				{
					map.put("path",map2.get("path").toString());
					break;
				}
			}
		}
		prr.setItems(ml);
		return prr;
	}

}
