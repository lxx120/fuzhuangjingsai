package com.match.systemconfig.service;

import com.match.common.result.ObjectResult;
import com.match.systemconfig.persist.Module;

public interface IModuleService {
	
	public Module getModuleByName(String name);
	
	public ObjectResult<Long> insertModule(Module module);
	
	
}
