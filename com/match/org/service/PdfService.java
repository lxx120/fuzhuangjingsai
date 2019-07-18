package com.match.org.service;

import java.util.Map;

import com.match.org.model.HuoJiangZhengShu;

public interface PdfService {

	
	public String  addPDFBaoMing(Map<String,Object> map,long id)  throws  Exception;
	
	public  String  PdfZhengshuFile(HuoJiangZhengShu huoJiangZhengShu)  throws  Exception;
}
