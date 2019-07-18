package org.flowable.ui.modeler.application;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/data/modeler/")
public class CustomModelerLogin {
	private Logger logger=LogManager.getLogger();
	@RequestMapping("account")  
    public Map account(HttpServletResponse resp) {
		Map<String, Object> map=new HashMap<String, Object>();
			map.put("id","20180909");
			map.put("login_name","hnjbjsynj");
			map.put("real_name","河南基本建设实验室");
			map.put("email",null);
			map.put("avatar_url",null);
			return map;
    }
}
