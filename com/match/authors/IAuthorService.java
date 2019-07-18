package com.match.authors;

import com.match.common.result.ObjectResult;

public interface IAuthorService {

	public ObjectResult<Boolean> checkAuthon(String module, int opValue, long userID,long enterprise);
}
