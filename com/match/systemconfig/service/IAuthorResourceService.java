package com.match.systemconfig.service;

import com.match.common.result.ObjectResult;
import com.match.systemconfig.persist.AuthorResource;

public interface IAuthorResourceService {
	
	public ObjectResult<Long> insert(AuthorResource ar);
	
	public ObjectResult<AuthorResource> update(AuthorResource ar);
	
	public AuthorResource getByURI(String uri);
	
	
}
