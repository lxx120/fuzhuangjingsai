package com.match.weixin.persist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.match.common.result.ObjectResult;
import com.match.springutils.SpringContext;
import com.match.weixin.util.PayConfig;
import com.match.weixin.util.WeixinPayUtil;

//记录微信企业支付记录
public class WeixinEnterprisePay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String payment_time;
	private String appid;
	private String mchid;
	private String partner_trade_no;
	private String nonce_str;
	private String sign;
	private String openid;
	private String check_name="FORCE_CHECK";
	private String re_user_name;
	private int amount;  //分
	private String desc;   //来自哪个商户哪个订单的货款
	private String spbill_create_ip;
	private String return_code;
	private String result_code;
	private String payment_no;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	
	public String getPartner_trade_no() {
		return partner_trade_no;
	}
	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCheck_name() {
		return check_name;
	}
	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}
	public String getRe_user_name() {
		return re_user_name;
	}
	public void setRe_user_name(String re_user_name) {
		this.re_user_name = re_user_name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}
	@Override
	public String toString() {
		this.nonce_str=WeixinPayUtil.nonce_str();
		
		String str="<xml><mch_appid>%s</mch_appid><mchid>%s</mchid><nonce_str>%s</nonce_str><partner_trade_no>%s</partner_trade_no><openid>%s</openid>"
				+"<check_name>%s</check_name><re_user_name>%s</re_user_name><amount>%s</amount><desc>%s!</desc><spbill_create_ip>%s</spbill_create_ip><sign>%s</sign></xml>";
		Map<String, String> map=new HashMap<String, String>();
		map.put("mch_appid", this.appid);
		map.put("mchid", this.mchid);
		map.put("nonce_str", this.nonce_str);
		map.put("partner_trade_no", this.partner_trade_no);
		map.put("openid", this.openid);
		map.put("check_name", this.check_name);
		map.put("re_user_name", this.re_user_name);
		map.put("amount", this.amount+"");
		map.put("desc", this.desc);
		map.put("spbill_create_ip",this.spbill_create_ip );
		PayConfig pc=SpringContext.getAppContext().getBean(PayConfig.class);
		this.sign=WeixinPayUtil.sign(map, pc.getWx_key());
		str=String.format(str, this.appid,this.mchid,this.nonce_str,this.partner_trade_no,this.openid,this.check_name,this.re_user_name,this.amount+"",this.desc,this.spbill_create_ip,this.sign);
		return str;
	}
	/**
	 * 使用的是平台微信的key
	 * @param str
	 * @return
	 */
	public ObjectResult<String> parseReturnStr(String str){
		ObjectResult<String> or=new ObjectResult<String>();
		PayConfig pc=SpringContext.getAppContext().getBean(PayConfig.class);
		Map<String, String> rrr=WeixinPayUtil.parseXML(str);
		String sign=rrr.get("sign");
		String tsign=WeixinPayUtil.sign(rrr, pc.getWx_key());
		if(!tsign.equalsIgnoreCase(sign)){
			or.setCode(1);
			or.setHint("校验错误!");
			return or;
		}
		if(!"SUCCESS".equals(rrr.get("result_code"))){
			or.setCode(1);
			or.setHint(rrr.get("result_code"));
			return or;
		}
		this.return_code=rrr.get("return_code");
		this.result_code=rrr.get("result_code");
		this.payment_no=rrr.get("payment_no");
		this.payment_time=rrr.get("payment_time");
		or.setCode(0);
		or.setHint("ok");
		return or;
	}
	
	
}
