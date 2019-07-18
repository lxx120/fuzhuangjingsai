package com.match.systemconfig.service;

import com.match.common.result.ObjectResult;
import com.match.systemconfig.persist.AuthorObject;

public interface IAuthorObjectService {
	
	public AuthorObject getByName(String name);

	public AuthorObject getByID(long id);

	public ObjectResult<Long> insert(AuthorObject ao);

}
