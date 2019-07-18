package com.match.org.service;

import org.springframework.web.multipart.MultipartFile;

public interface PoiService {

	public int  writeExcle(MultipartFile file,long divisionid)  throws  Exception;
}
