package com.match.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.news.mapper.ContentFileMapper;
import com.match.news.model.ContentFile;
import com.match.news.service.ContentFileService;

@Service
public class ContentFileServiceImpl implements ContentFileService {

	@Autowired
	private  ContentFileMapper  ContentFileMapper;
	
	@Override
	public boolean addContentFile(ContentFile contentFile) throws Exception {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		contentFile.setMtime(sdf.parse(sdf.format(new Date())));
		
		int i =  ContentFileMapper.insertSelective(contentFile);
		if(i>0)
		{
			return  true;
		}
		return  false;
	}

	@Override
	public List<Map<String, Object>> findContentFileList(long id) throws Exception {
		// TODO Auto-generated method stub
		
		return ContentFileMapper.findContentFileList(id);
	}

	@Override
	public boolean deleteContentFile(long id) throws Exception {
		// TODO Auto-generated method stub
		int i =  ContentFileMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return  true;
		}
		return  false;
	}

}
