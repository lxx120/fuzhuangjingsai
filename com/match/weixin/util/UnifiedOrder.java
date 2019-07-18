package com.match.weixin.util;

import java.util.Date;
import java.util.Map;
public class UnifiedOrder {
	
	private String out_trade_no;//定单号
	private String body;
	private String detail;
	private String attach;
	private String fee_type;
	private String total_fee;  //单位为分
	private String spbill_create_ip;
	private Date time_start;  //yyyyMMddHHmmss
	private Date time_expire; //yyyyMMddHHmmss
	private String goods_tag;
	private WXTradeType trade_type;
	private String product_id;
	private String limit_pay;
	private String openid;
	
	
	private String result_code;///返回结果
	private String return_code;
	private String err_code;
	private String err_code_des;
	
	private String prepay_id;
	private String code_url;//二维码支付的二维码路径
	private String mweb_url;//h5支付的页面
	
	private String nonce_str;
	private String sign;
	
	private String return_nonce_str;
	private String return_sign;
	private String return_msg;
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public Date getTime_start() {
		return time_start;
	}
	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}
	public Date getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(Date time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public WXTradeType getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(WXTradeType trade_type) {
		this.trade_type = trade_type;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
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
	public String getReturn_nonce_str() {
		return return_nonce_str;
	}
	public void setReturn_nonce_str(String return_nonce_str) {
		this.return_nonce_str = return_nonce_str;
	}
	public String getReturn_sign() {
		return return_sign;
	}
	public void setReturn_sign(String return_sign) {
		this.return_sign = return_sign;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getMweb_url() {
		return mweb_url;
	}
	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}
	
	
	public String toString(String app_id,String mch_id,String notify_url)
	{
		String res=null;
		if(this.trade_type==WXTradeType.JSAPI)
		{
			String str="<xml><appid>%s</appid><attach>%s</attach><body>%s</body><mch_id>%s</mch_id><nonce_str>%s</nonce_str><notify_url>%s</notify_url><openid>%s</openid><out_trade_no>%s</out_trade_no><spbill_create_ip>%s</spbill_create_ip><total_fee>%s</total_fee><trade_type>%s</trade_type><sign>%s</sign></xml>";
			res=String.format(str, app_id,attach,body,mch_id,nonce_str,notify_url,openid,out_trade_no,spbill_create_ip,total_fee,trade_type,sign);
		}else{
			String str="<xml><appid>%s</appid><attach>%s</attach><body>%s</body><mch_id>%s</mch_id><nonce_str>%s</nonce_str><notify_url>%s</notify_url><out_trade_no>%s</out_trade_no><spbill_create_ip>%s</spbill_create_ip><total_fee>%s</total_fee><trade_type>%s</trade_type><sign>%s</sign></xml>";
			res=String.format(str,app_id,attach,body,mch_id,nonce_str,notify_url,out_trade_no,spbill_create_ip,total_fee,trade_type,sign);
		}
		return res;
	}
	
	public boolean parse(String result,String key){
		try{
		    Map<String, String> mp=WeixinPayUtil.parseXML(result);
		    if(mp==null)
		    	return false;
		    String sign=mp.get("sign");
		    mp.remove("sign");
		    String str=WeixinPayUtil.concatStr(mp);
		    str=str+"&key="+key;
		    String tsign=WeixinPayUtil.sign(str);
		    if(!tsign.equals(sign))
		    	return false;
		    this.setReturn_code(mp.get("return_code"));
		    this.setResult_code(mp.get("result_code"));
		    this.setReturn_msg(mp.get("return_msg"));
		    if(!"SUCCESS".equals(this.getReturn_code())){
		    	return false;
		    }
		    this.setReturn_nonce_str(mp.get("nonce_str"));
		    this.setReturn_sign(mp.get("sign"));
		    this.setPrepay_id(mp.get("prepay_id"));
		    this.setCode_url(mp.get("code_url"));
		    this.setMweb_url(mp.get("mweb_url"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
}
