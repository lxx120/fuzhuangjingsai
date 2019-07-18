package com.match.org.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.org.service.ReadFilesDirService;
import com.match.proposition.mapper.ThemeWorkFileMapper;
import com.match.proposition.mapper.ThemeWorkMapper;

@Service
public class ReadFilesDir implements  ReadFilesDirService {

	@Autowired
	private  ThemeWorkFileMapper  themeWorkFileMapper;
	
	@Autowired
	private  ThemeWorkMapper  themeWorkMapper;
	
	public int readFilesDir(String path,long competitionid) throws  Exception{
		
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		List<Map<String,Object>> themelist = themeWorkMapper.findThemeCode(competitionid);
		List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : themelist) {
			String twcode = map.get("twcode").toString();
			long id = Long.parseLong(map.get("id").toString());
			for (File file : files) {
				// 处理文件内容
				String filename  = file.getName().substring(0, 15);
				if(twcode.equals(filename))
				{
					String path1 = "/"+"zuopin/"+file.getName();
					Map<String,Object> mapz = new HashMap<>();
					mapz.put("mtime", UserChangLiang.mtime());
					mapz.put("code", file.getName().substring(0, 17));
					mapz.put("name", file.getName().substring(0, 17));
					mapz.put("size", (double)file.length());
					mapz.put("path", path1);
					mapz.put("themeworkid", id);
					list.add(mapz);
				}
					
			}
		}
		
		int i = themeWorkFileMapper.BatchAddThemeWorkFile(list);
		if(i>0)
		{
			return 1;
		}
		return 0;

	}
}
