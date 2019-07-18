package com.match.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.match.mall.persist.GoodsType;
import com.match.mall.persist.GoodsValue;
import com.match.mall.persist.Quantity;

public interface GoodstypeMapper {

	List<Map<String, Object>> gettypeList();

	List<Map<String, Object>> getquantityList(@Param("typeid")long typeid,@Param("page")int page, @Param("pagesize")int pagesize);

	int count(@Param("typeid")long typeid);

	List<Map<String, Object>> getchild(@Param("id")long id);

	void del(long typeid);

	void dosave(GoodsType g);

	void addQuantity(Quantity q);

	void addvalue(@Param("quantityid")long quantityid,@Param("quantity") String quantity, @Param("value")String value);

	void delQuantity(@Param("quantityid")long quantityid);

	void delValue(@Param("quantityid")long quantityid);

	void delgoodsValue(@Param("valueid")long valueid);

	void update(GoodsType g);

	int counttype(@Param("id")long id);

	GoodsType getDetail(@Param("id")long id);

	void addGoodsValue(GoodsValue g);

	void editvalue(@Param("quantityid")long quantityid,@Param("quantity") String quantity,@Param("value") String value);

	void updatequantity(@Param("quantityid")long quantityid,@Param("quantity") String quantity);

	void updategoodsvalue(@Param("quantityid")long quantityid, @Param("quantity")String quantity);

	int getIsuse(@Param("quantityid")long quantityid);

}
