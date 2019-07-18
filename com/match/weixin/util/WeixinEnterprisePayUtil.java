package com.match.weixin.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.match.common.Httpmethod;
import com.match.common.Jacksonmethod;
import com.match.common.result.ObjectResult;
import com.match.weixin.persist.WeixinEnterprisePay;
@Service
public class WeixinEnterprisePayUtil {
	@Resource
	private PayConfig payConfig;
	@Resource
	private Httpmethod htm;
	private Logger log=LoggerFactory.getLogger(WeixinEnterprisePayUtil.class);
	private static String enterprisePayUri="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	private static String queryEnPayUri="https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
	public ObjectResult<WeixinEnterprisePay> pay(WeixinEnterprisePay wxep){
		ObjectResult<WeixinEnterprisePay> or=new ObjectResult<WeixinEnterprisePay>();
		String body=wxep.toString();
		String res=htm.ssl_postbody(enterprisePayUri, body);
		ObjectResult<String> or1=wxep.parseReturnStr(res);
		or.setItem(wxep);
		or.setCode(or1.getCode());
		or.setHint(or1.getHint());
		return or;
	}
	/**
	 * 
	 * @param partner_trade_no
	 * @return
	 * coe1
	 * 
	 */
	public ObjectResult<WeixinEnterprisePay> queryEPay(String partner_trade_no){
		ObjectResult<WeixinEnterprisePay> or=new ObjectResult<WeixinEnterprisePay>();
		Map<String, String> mp=new HashMap<String, String>();
		mp.put("partner_trade_no", partner_trade_no);
		mp.put("mch_id",payConfig.getWx_mch_id() );
		mp.put("appid", payConfig.getWx_appid());
		mp.put("nonce_str", WeixinPayUtil.nonce_str());
		String sign=WeixinPayUtil.sign(mp, payConfig.getWx_key());
		mp.put("sign",sign);
		String body="<xml><sign><![CDATA[%s]]></sign><partner_trade_no><![CDATA[%s]]></partner_trade_no><mch_id ><![CDATA[%s]]></mch_id ><appid><![CDATA[%s]]></appid> <nonce_str><![CDATA[%s]]></nonce_str></xml>";
		String.format(body, sign,partner_trade_no,payConfig.getWx_mch_id(),payConfig.getWx_appid(),mp.get("nonce_str"));
		String res=htm.ssl_postbody(queryEnPayUri, body);
		WeixinEnterprisePay wxep=new WeixinEnterprisePay();
		ObjectResult<String> or1=wxep.parseReturnStr(res);
		if("SUCCESS".equals(wxep.getReturn_code())&&"SUCCESS".equals(wxep.getResult_code())){
			or.setCode(1);
			or.setHint("已经支付过,不需要再次支付");
			or.setItem(wxep);
		}else{
			or.setCode(0);
		}
		
		return or;
	}
}
