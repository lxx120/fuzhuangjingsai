package com.match.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.match.baciss.model.User;
import com.match.baciss.service.UserServices;
import com.match.common.BasicMethod;
import com.match.common.Jacksonmethod;
import com.match.common.result.PageRequest;
import com.match.dao.LftrgtDao;
import com.match.mall.service.IGoodsService;
import com.match.org.service.impl.ReadFilesDir;
import com.match.proposition.service.CompetitionService;


public class MapperTest {
	private Logger log=LoggerFactory.getLogger(MapperTest.class);
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationcontext.xml");
	IGoodsService goods=ac.getBean(IGoodsService.class);
	UserServices  userservice = ac.getBean(UserServices.class);
	CompetitionService com = ac.getBean(CompetitionService.class);
	@Test
	public void testEnterprise(){
//		EnterpriseMapper cm=ac.getBean(EnterpriseMapper.class);
//		Enterprise e = new Enterprise();
//		e.setAddress("cs");
//		e.setCreator("admin");
//		e.setCreditCode("测试");
//		e.setDistrict("测试");
//		e.setName("测试");
//		e.setPid(0);
//		e.setValid(false);
//		e.setId(2);
//		log.info(Jacksonmethod.tojson(cm.getSum(""), true));
//		log.info(Jacksonmethod.tojson(cm.getenterpriseList("",0,20), true));
//		log.info(Jacksonmethod.tojson(cm.insert(e), true));
//		log.info(Jacksonmethod.tojson(cm.delenterprise(2), true));
//		log.info(Jacksonmethod.tojson(enterprise.getenterpriseserList("测试",new PageRequest(0, 20, null)), true));
//		log.info(Jacksonmethod.tojson(goods.makeSku(14), true));
	}
	
	@Test
	public void testEnterprise1(){
		User user = new User();
		user.setPhone("123456789");
		try {
			int falg = userservice.adduserByPhone(user);
			System.out.println(falg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
//	@Test
//	public void readFilesDir(){
//		  String path = "D:/111";
//	      ReadFilesDir.readFilesDir(path);  
//		
//	}
	
	@Test
	public void testEnterprise2(){
		try {
			com.updateCompetitionStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
