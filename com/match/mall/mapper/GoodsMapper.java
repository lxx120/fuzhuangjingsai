package com.match.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.match.mall.persist.Goods;
import com.match.mall.persist.GoodsValue;
import com.match.mall.persist.Quantity;


public interface GoodsMapper {

	int count(Goods goods);

	List<Map<String, Object>> getgoodsList(@Param("name")String name,@Param("goodstype") long goodstype,@Param("page") int start, @Param("pagesize")int pageSize);

	void delete(@Param("goodsid")long goodsid);

	Goods getById(@Param("goodsid")long goodsid);

	void addGoods(Goods goods);

	void addGoodsvalue(@Param("goodsid")long goodsid, @Param("goodsvalueid")long goodsvalueid, @Param("quantityid")long quantityid, @Param("quantity")String quantity, @Param("value")String value);

	GoodsValue getgoodsvalue(@Param("goodsvalueid")long goodsvalueid);

	Map<String, Object> getDetail(@Param("goodsid")Long goodsid);

	List<Map<String, Object>> getquantity(@Param("goodstype")long goodstype);

	List<Map<String, Object>> getvalue(@Param("quantityid")long quantityid);

	List<Map<String, Object>> getcheckvalue(@Param("quantityid")long quantityid, @Param("goodsid")Long goodsid);

	void updateGoods(Goods goods);

	void deletevalue(@Param("goodsid")long goodsid);

	void addImg(@Param("goodsid")long id,@Param("url") String url);

	void delImg(@Param("goodsid")long id);

	List<Long> getQuantityidList(@Param("goodsid")long goodsid);

	List<String> getgvalue(@Param("quantityid")Long long1, @Param("goodsid")long goodsid);

	void addSku(@Param("goodsid")long goodsid,@Param("salevalues") String salevalues);

	void addskuprice(@Param("id")long id,@Param("price") double price, @Param("marketprice")double marketprice);

	int countsku(@Param("goodsid")long goodsid);

	List<Map<String, Object>> getskuList(@Param("goodsid")long goodsid, @Param("page")int page, @Param("pagesize")int pagesize);

	void delSku(@Param("goodsid")long goodsid);

	List<Map<String, Object>> getImgs(@Param("goodsid")long goodsid);

}
