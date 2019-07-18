package com.match.mall.service;

import java.util.Map;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.mall.persist.GoodsType;
import com.match.mall.persist.GoodsValue;

public interface IGoodstypeService {

	PageResult<Map<String, Object>> gettypeList();

	PageResult<Map<String, Object>> getQuantity(long typeid, PageRequest pr);

	ObjectResult<Map<String, Object>> delType(long typeid);

	ObjectResult<GoodsType> doSave(GoodsType g);

	ObjectResult<Map<String, Object>> addQuantity(String quantityInfo);

	ObjectResult<Map<String, Object>> delQuantity(long quantityid);

	ObjectResult<Map<String, Object>> delGoodsvalue(long valueid);

	ObjectResult<GoodsType> edit(GoodsType g);

	ObjectResult<GoodsType> getDetail(long id);

	ObjectResult<Map<String, Object>> addGoodsValue(GoodsValue g);

	ObjectResult<Map<String, Object>> editQuantity(String quantityInfo);

}
