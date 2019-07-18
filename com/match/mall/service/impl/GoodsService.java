package com.match.mall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.match.common.skuMergeUtil;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.mall.mapper.GoodsMapper;
import com.match.mall.persist.Goods;
import com.match.mall.persist.GoodsValue;
import com.match.mall.service.IGoodsService;

@Service
public class GoodsService implements IGoodsService {

	@Resource
	GoodsMapper goodsper;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public PageResult<Map<String, Object>> getgoodsList(Goods goods, PageRequest pr) {
		PageResult<Map<String, Object>> prr = new PageResult<Map<String, Object>>();
		if (pr == null) {
			pr = new PageRequest(0, 30, null);
			pr.setNeedPages(true);
		}
		if (pr.isNeedPages()) {
			int count = goodsper.count(goods);
			prr.setPages(Daomethod.countpages(count, pr.getPageSize()));
			prr.setRowCount(count);
		}
		try {
			List<Map<String, Object>> gl = goodsper.getgoodsList(goods.getName(), goods.getGoodstype(), pr.getStart(),
					pr.getPageSize());
			for(int i = 0;i<gl.size();i++) {
				List<Map<String, Object>> imgs = goodsper.getImgs(Long.parseLong(gl.get(i).get("id").toString()));
				gl.get(i).put("imgs", imgs);
			}
			prr.setCode(0);
			prr.setItems(gl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prr;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ObjectResult<Map<String, Object>> del(long goodsid) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		if (0 == goodsid) {
			orr.setCode(1);
			orr.setErrmsg("id不能为空");
		}
		Goods goods = goodsper.getById(goodsid);
		if (goods != null) {
			goodsper.delete(goodsid);
			orr.setCode(0);
			orr.setErrmsg("删除成功");
		} else {
			orr.setCode(1);
			orr.setErrmsg("对象不存在");
		}
		return orr;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ObjectResult<Map<String, Object>> addGoods(Goods goods, String quantityInfo, String imgs) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		JSONObject jsonObject = new JSONObject(quantityInfo);
		List<Object> il = jsonObject.getJSONArray("goodsvalueid").toList();
		JSONObject imgObject = new JSONObject(imgs);
		List<Object> imgl = imgObject.getJSONArray("url").toList();
		try {
			goodsper.addGoods(goods);
		} catch (Exception e) {
			e.getStackTrace();
			System.err.println(e.getMessage());
		}
		if (quantityInfo != null && quantityInfo != "") {
			if (il.size() > 0) {
				for (int i = 0; i < il.size(); i++) {
					GoodsValue gv = goodsper.getgoodsvalue(Long.parseLong(il.get(i).toString()));
					goodsper.addGoodsvalue(goods.getId(), gv.getId(), gv.getQuantityid(), gv.getQuantity(),
							gv.getValue());
				}
			}
		}
		if (imgs != null && imgs != "") {
			if (imgl.size() > 0) {
				for (int i = 0; i < imgl.size(); i++) {
					goodsper.addImg(goods.getId(), "http://277.56.182.143:8888/" + imgl.get(i).toString());
				}
			}
		}
		if(makeSku(goods.getId())!=null) {
			List<String> sku = makeSku(goods.getId());
			for(int i=0;i<sku.size();i++) {
				goodsper.addSku(goods.getId(),sku.get(i));
			}
		}
		orr.setCode(0);
		orr.setErrmsg("添加成功");
		return orr;
	}

	public List<String> makeSku(long goodsid){
		List<Long> quantityidList = goodsper.getQuantityidList(goodsid);
		List<List<String>> quan = new ArrayList();
		List<String> sku = new ArrayList();
		if(quantityidList.size()>0) {
			for(int i =0;i<quantityidList.size();i++) {
				List<String> value = goodsper.getgvalue(quantityidList.get(i),goodsid);
				quan.add(value);
			}
		}
		if(quan.size()>0) {
			sku = skuMergeUtil.mergeSkudes(quan);
		}
		return sku;
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public ObjectResult<Map<String, Object>> goodsDetail(Long goodsid) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		Map result = new HashMap();
		Map<String, Object> g = goodsper.getDetail(goodsid);
		List<Map<String, Object>> ql = goodsper.getquantity(Long.parseLong(g.get("goodsType").toString()));
		if (ql.size() > 0) {
			for (int i = 0; i < ql.size(); i++) {
				List<Map<String, Object>> allvalue = goodsper.getvalue(Long.parseLong(ql.get(i).get("id").toString()));
				System.err.println(allvalue);
				List<Map<String, Object>> checkedvalue = goodsper
						.getcheckvalue(Long.parseLong(ql.get(i).get("id").toString()), goodsid);
				System.err.println(allvalue);
				ql.get(i).put("allvalue", allvalue);
				ql.get(i).put("checkedvalue", checkedvalue);
			}
		}
		result.put("goods", g);
		result.put("quantity", ql);
		orr.setCode(0);
		orr.setErrmsg("查询成功");
		orr.setItem(result);
		return orr;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ObjectResult<Map<String, Object>> editGoods(Goods goods, String quantityInfo, String imgs) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		Goods g = goodsper.getById(goods.getId());
		if (g == null) {
			orr.setCode(1);
			orr.setErrmsg("对象不存在");
			return orr;
		}
		try {
			goodsper.updateGoods(goods);
			goodsper.deletevalue(goods.getId());
		} catch (Exception e) {
			// e.getStackTrace();
			System.err.println(e.getMessage());
		}
		if (quantityInfo != null && quantityInfo != "") {
			JSONObject jsonObject = new JSONObject(quantityInfo);
			List<Object> il = jsonObject.getJSONArray("goodsvalueid").toList();
			if (il.size() > 0) {
				for (int i = 0; i < il.size(); i++) {
					GoodsValue gv = goodsper.getgoodsvalue(Long.parseLong(il.get(i).toString()));
					goodsper.addGoodsvalue(goods.getId(), gv.getId(), gv.getQuantityid(), gv.getQuantity(),
							gv.getValue());
				}

			}
		}
		if (imgs != null && imgs != "") {
			JSONObject imgObject = new JSONObject(imgs);
			List<Object> imgl = imgObject.getJSONArray("url").toList();
			if (imgl.size() > 0) {
				for (int i = 0; i < imgl.size(); i++) {
					goodsper.delImg(goods.getId());
					goodsper.addImg(goods.getId(), "http://123.56.82.143:8888/" + imgl.get(i).toString());
				}
			}
		}
		if(makeSku(goods.getId())!=null) {
			goodsper.delSku(goods.getId());
			List<String> sku = makeSku(goods.getId());
			for(int i=0;i<sku.size();i++) {
				goodsper.addSku(goods.getId(),sku.get(i));
			}
		}
		orr.setCode(0);
		orr.setErrmsg("修改成功");
		return orr;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ObjectResult<Map<String, Object>> saveskuPrice(String skuInfo) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		JSONObject skuObject = new JSONObject(skuInfo);
		List<Object> idList = skuObject.getJSONArray("id").toList();
		double price = skuObject.getDouble("price");
		double marketprice = skuObject.getDouble("marketprice");
		if(idList.size()<=0) {
			orr.setCode(1);
			orr.setErrmsg("sku不存在");
			return orr;
		}
		for(int i = 0;i<idList.size();i++) {
			long id =  Long.parseLong(idList.get(i).toString()) ;
			goodsper.addskuprice(id,price,marketprice);
		}
		return orr;
	}

	@Override
	public PageResult<Map<String, Object>> getskuList(long goodsid, PageRequest pr) {
		PageResult<Map<String, Object>> prr = new PageResult<Map<String, Object>>();
		if (pr == null) {
			pr = new PageRequest(0, 30, null);
			pr.setNeedPages(true);
		}
		if (pr.isNeedPages()) {
			int count = goodsper.countsku(goodsid);
			prr.setPages(Daomethod.countpages(count, pr.getPageSize()));
			prr.setRowCount(count);
		}
		try {
			List<Map<String, Object>> gl = goodsper.getskuList(goodsid, pr.getStart(),
					pr.getPageSize());
			prr.setCode(0);
			prr.setItems(gl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prr;
	}

}
