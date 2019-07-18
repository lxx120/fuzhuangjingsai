package com.match.mall.service;

import java.util.Map;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.mall.persist.Goods;

public interface IGoodsService {

	PageResult<Map<String, Object>> getgoodsList(Goods goods, PageRequest pr);

	ObjectResult<Map<String,Object>> del(long goodsid);

	ObjectResult<Map<String, Object>> addGoods(Goods goods, String quantityInfo, String imgs);

	ObjectResult<Map<String, Object>> goodsDetail(Long goodsid);

	ObjectResult<Map<String, Object>> editGoods(Goods goods, String quantityInfo, String imgs);

	ObjectResult<Map<String, Object>> saveskuPrice(String skuInfo);

	PageResult<Map<String, Object>> getskuList(long goodsid, PageRequest pr);


}
