package com.match.mall.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.dao.LftrgtDao;
import com.match.dao.common.Daomethod;
import com.match.mall.mapper.GoodstypeMapper;
import com.match.mall.mapper.OrderMapper;
import com.match.mall.persist.GoodsType;
import com.match.mall.persist.GoodsValue;
import com.match.mall.persist.Quantity;
import com.match.mall.service.IGoodstypeService;
@Service
public class GoodstypeService implements IGoodstypeService {

	@Resource
	private GoodstypeMapper typeper;
	
	@Resource
	private LftrgtDao lfdao;
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public PageResult<Map<String, Object>> gettypeList() {
		PageResult<Map<String, Object>> prr = new PageResult<Map<String, Object>>();
		try{
			List<Map<String,Object>> tl = typeper.gettypeList();
			for(int i = 0;i<tl.size();i++) {
				if(((List<Map<String,Object>>) tl.get(i).get("children")).size()>0) {
					List<Map<String,Object>> children = (List<Map<String, Object>>) tl.get(i).get("children");
					for(int j = 0;j<children.size();j++) {
						List<Map<String,Object>> child = typeper.getchild(Long.parseLong(children.get(j).get("id").toString()));
						   if(child.size()>0) {
							   for(int k = 0;k<child.size();k++) {
									List<Map<String,Object>> childsun = typeper.getchild(Long.parseLong(child.get(k).get("id").toString()));
									child.get(k).put("children", childsun);
							   }
						   }
						children.get(j).put("children", child);
					}
					tl.get(i).put("children", children);
				}
			}
			prr.setCode(0);
			prr.setItems(tl);
			prr.setErrmsg("查询成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return prr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public PageResult<Map<String, Object>> getQuantity(long typeid,PageRequest pr) {
		PageResult<Map<String, Object>> prr = new PageResult<Map<String, Object>>();
		if(pr==null){
			pr=new PageRequest(0,30,null);
			pr.setNeedPages(true);
		}
		if(pr.isNeedPages()){
			int count=typeper.count(typeid);
			prr.setPages(Daomethod.countpages(count, pr.getPageSize()));
			prr.setRowCount(count);
		}
		try{
			List<Map<String,Object>> ql = typeper.getquantityList(typeid,pr.getStart(),pr.getPageSize());
			prr.setCode(0);
			prr.setItems(ql);
			prr.setErrmsg("查询成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return prr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> delType(long typeid) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		if(typeid<=0){
			orr.setCode(1);
			orr.setErrmsg("id为空");
			return orr;
		}
		try{
			lfdao.delete("goodstype", typeid,  null);
			typeper.del(typeid);
			orr.setCode(0);
			orr.setErrmsg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<GoodsType> doSave(GoodsType g) {
		ObjectResult<GoodsType> orr = new ObjectResult<GoodsType>();
		try{
			typeper.dosave(g);
			lfdao.insert("goodstype", g.getId(), g.getPid(), 1, null);
			orr.setCode(0);
			orr.setErrmsg("添加成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> addQuantity(String quantityInfo) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		JSONObject jsonObject = new JSONObject(quantityInfo);
		long typeid=Long.parseLong(jsonObject.get("typeid").toString());
		String name = jsonObject.getString("name");
		Quantity q = new Quantity();
		q.setName(name);
		q.setGoodstype(typeid);
		try{
			typeper.addQuantity(q);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Object> value = jsonObject.getJSONArray("value").toList();
		if(value.size()>0) {
			for(int i = 0;i<value.size();i++) {
				try{
					typeper.addvalue(q.getId(),q.getName(),value.get(i).toString());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			orr.setCode(0);
			orr.setErrmsg("添加成功");
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> delQuantity(long quantityid) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		int isuse = typeper.getIsuse(quantityid);
		if(isuse>0) {
			orr.setCode(1);
			orr.setErrmsg("已有商品在使用不能删除");
			return orr;
		}
		try{
			typeper.delQuantity(quantityid);
			typeper.delValue(quantityid);
			orr.setCode(0);
			orr.setErrmsg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> delGoodsvalue(long valueid) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		try{
			typeper.delgoodsValue(valueid);
			orr.setCode(0);
			orr.setErrmsg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<GoodsType> edit(GoodsType g) {
		ObjectResult<GoodsType> orr = new ObjectResult<GoodsType>();
		int isexist = typeper.counttype(g.getId());
		if(isexist>0) {
			try{
				typeper.update(g);
				orr.setCode(0);
				orr.setErrmsg("修改成功");
			}catch(Exception e){
				e.printStackTrace();
			}	
		}else {
			orr.setCode(1);
			orr.setErrmsg("对象不存在");
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<GoodsType> getDetail(long id) {
		ObjectResult<GoodsType> orr = new ObjectResult<GoodsType>();
		try{
			GoodsType gt = typeper.getDetail(id);
			orr.setCode(0);
			orr.setErrmsg("查找成功");
			orr.setItem(gt);
		}catch(Exception e){
			e.printStackTrace();
		}	
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> addGoodsValue(GoodsValue g) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		try{
			typeper.addGoodsValue(g);
			orr.setCode(0);
			orr.setErrmsg("添加成功");
		}catch(Exception e){
			e.printStackTrace();
		}	
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> editQuantity(String quantityInfo) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		JSONObject jsonObject = new JSONObject(quantityInfo);
		long quantityid=Long.parseLong(jsonObject.get("quantityid").toString());
		String quantity = jsonObject.getString("quantity");
		try{
			typeper.updatequantity(quantityid,quantity);
			typeper.updategoodsvalue(quantityid,quantity);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Object> value = jsonObject.getJSONArray("value").toList();
		if(value.size()>0) {
			for(int i = 0;i<value.size();i++) {
				try{
					typeper.editvalue(quantityid,quantity,value.get(i).toString());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		orr.setCode(0);
		orr.setErrmsg("编辑成功");
		return orr;
	}	

}
