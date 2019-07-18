package com.match.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.match.common.result.ObjectResult;
import com.match.springmvc.annotations.PermissionAnno;
import com.match.springmvc.penum.OperationEnum;
import com.match.systemconfig.enums.ResourceType;
import com.match.systemconfig.persist.AuthorObject;
import com.match.systemconfig.persist.AuthorOperation;
import com.match.systemconfig.persist.AuthorResource;
import com.match.systemconfig.persist.Module;
import com.match.systemconfig.service.IAuthorObjectService;
import com.match.systemconfig.service.IAuthorOperationService;
import com.match.systemconfig.service.IAuthorResourceService;
import com.match.systemconfig.service.IModuleService;


@RestController("/resource/regist")
public class ResourceRegistController {
//	private Logger log=LogManager.getLogger();
//	@Resource
//	private IModuleService imSer;
//	@Resource
//	private IAuthorObjectService iaoSer;
//	@Resource
//	private IAuthorOperationService iaopSer;
//	@Resource
//	private IAuthorResourceService iarSer;
//	@Autowired  
//    private RequestMappingHandlerMapping requestMappingHandlerMapping; 
//	
//	@RequestMapping("/urls.htm")
//	public String allurl(HttpServletRequest r, HttpServletResponse re) {
//		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
//				.getHandlerMethods();
//		Map<String, AuthorObject> objs=new HashMap<>();
//		Map<String,Module> mos=new HashMap<>();
//		Map<String, AuthorOperation> ops=new HashMap<>();
//		
//		for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
//			RequestMappingInfo info = m.getKey();
//			HandlerMethod method = m.getValue();
//			PermissionAnno pa=method.getMethod().getAnnotation(PermissionAnno.class);
//			if(pa==null)
//				continue;
//			PatternsRequestCondition p = info.getPatternsCondition();
//			String url1=null;
//			for (String url : p.getPatterns()) {
//				url1=url;
//			}
//			String className=method.getMethod().getDeclaringClass().getName();
//			String methodName=method.getMethod().getName();
//			log.info(" the url and method is:::",className,methodName,url1);
//			//////////////////////////////////////////////
//			String moduleName=pa.module();
//			Module module=mos.get(moduleName);
//			if(module==null){
//				module=this.imSer.getModuleByName(moduleName);
//			}
//			
//			if(module==null){
//				module=new Module();
//				module.setModuleName(moduleName);
//				module.setCode(moduleName);
//				module.setModuleDesc(moduleName);
//				
//				ObjectResult<Long> or=this.imSer.insertModule(module);
//				if(or.getCode()==0){
//					module.setId(or.getItem());
//					mos.put(moduleName, module);
//				}
//			}else{
//				mos.put(moduleName, module);
//			}
//			////////////////////////////////////////////////////
//			String authorObjectName=pa.authorObject();
//			AuthorObject ao=objs.get(authorObjectName);
//			if(ao==null){
//				ao=this.iaoSer.getByName(authorObjectName);
//			}
//			if(ao==null){
//				ao=new AuthorObject();
//				ao.setModule(module.getId());
//				ao.setName(authorObjectName);
//				ao.setNote("");
//				ObjectResult<Long> or=this.iaoSer.insert(ao);
//				if(or.getCode()==0){
//					ao.setId(or.getItem());
//					objs.put(authorObjectName, ao);
//				}
//			}else{
//				objs.put(authorObjectName, ao);
//			}
//			//////////////////////////////////////
//			OperationEnum authorOperationName=pa.authorOperation();
//			AuthorOperation aop=ops.get(authorOperationName+"");
//			if(aop==null){
//				aop=this.iaopSer.getByName(authorOperationName);
//			}
//			if(aop==null){
//				int exp=this.iaopSer.maxExp();
//				aop=new AuthorOperation();
//				aop.setExp(exp+1);
//				aop.setModule(module.getId());
//				aop.setName(authorOperationName+"");
//				aop.setValue((int)Math.pow(2, exp));
//				ObjectResult<Long> or=this.iaopSer.insert(aop);
//				if(or.getCode()==0){
//					aop.setId(or.getItem());
//					ops.put(authorOperationName+"", aop);
//				}
//			}else{				
//				ops.put(authorOperationName+"", aop);
//			}
//			
//			AuthorResource ar=this.iarSer.getByURI(url1);
//			if(ar==null){
//				ar=new AuthorResource();
//			}
//			ar.setAuthorObject(ao.getId());
//			ar.setAuthorOperation(aop.getValue());
//			ar.setModule(module.getId());
//			ar.setType(ResourceType.页面);
//			ar.setUri(url1);
//			if(ar.getId()==0){				
//				this.iarSer.insert(ar);
//			}else{
//				this.iarSer.update(ar);
//			}
//		}
//		return null;
//	}
}
