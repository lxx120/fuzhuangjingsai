package com.match.mybasit.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.match.data.author.persist.Rules;


@Service
public class DataAuthorPool {
	private Map<String, Rules> map=new HashMap<String, Rules>();
	@PostConstruct
	public void init(){
		
	}
	public Rules getByAuthorID(String dataAuthorID){
		return map.get(dataAuthorID);
	}
}
