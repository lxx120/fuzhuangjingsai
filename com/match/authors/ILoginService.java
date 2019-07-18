package com.match.authors;

import com.match.common.result.ObjectResult;
import com.match.systemconfig.persist.Login;

public interface ILoginService {
	public ObjectResult<Login> checkLoginByToken(String token);
}
