package com.match.systemconfig.service;

import com.match.common.result.ObjectResult;
import com.match.springmvc.penum.OperationEnum;
import com.match.systemconfig.persist.AuthorOperation;

public interface IAuthorOperationService {
	
	public ObjectResult<Long> insert(AuthorOperation ao);
	
	public AuthorOperation getByName(OperationEnum op);
	
	public int maxExp();
	
}
