package com.match.weixin.service;

import com.match.common.result.ObjectResult;
import com.match.weixin.persist.Wechat;

public interface IWechatService {
	/**
	 * 1. 如果数据库没有openid,则插入wechat,其中fromMerchant=wholesaler;
	 * 2. 如果数据库有openid对应的wechat，并且userid为0,则 update fromMerchant=wholesaler;
	 * 3. 如果openid对应的wechat，并且userid不为0,则根据wholesaler的配置，直接生成customer或者是customerapply，这个时候，frommerchant为0
	 *code:0的正确执行，hint填写好结果
	 *code:1，不能正确执行
	 * @param openid
	 * @param wholesaler
	 * @return
	 */
	public ObjectResult<Wechat> wechatScanWholesaler(String openid,long wholesaler);
	
	public Wechat getWechat(String openid);
	
	public ObjectResult<Boolean> saveWechat(Wechat wechat);
	/**
	 * 1. 如果frommerchat为该用户的wholesaler则仅仅绑定即可，
	 * 2. 如果frommerchat不是该用户的wholesaler，则根据供应商配置添加customer或者customerapply
	 * 3. 如果wechat的frommerchant不为0，则将userid对应的商户设置为frommerchant的私有商户
	 * @param openid
	 * @param userid
	 * @return
	 */
	public ObjectResult<Wechat> bindWechatWithUserid(String openid,long userid);
	/**
	 * 把wechat的userid设置为0
	 * @param openid
	 * @return
	 */
	public ObjectResult<Boolean> qxbdWechWithUserid(String openid);
}
