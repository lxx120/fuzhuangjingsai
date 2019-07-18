package com.match.weixin.persist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.match.common.ConstantVar;
import com.match.weixin.util.WeixinPayUtil;


public class MerchantWXConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long merchant;
	private String wx_appid;
	private String wx_mch_id;
	private String wx_key;
	private String mp_txtName;
	private String mp_txtContent;
	private boolean valid;
	private String nonce_str;  // 类似于微信的uuid随机字符串
	private String sign;//  签名，主要是为了防止别人随意入侵数据库篡改数据
	public long getMerchant() {
		return merchant;
	}
	public void setMerchant(long merchant) {
		this.merchant = merchant;
	}
	public String getWx_appid() {
		return wx_appid;
	}
	public void setWx_appid(String wx_appid) {
		this.wx_appid = wx_appid;
	}
	public String getWx_mch_id() {
		return wx_mch_id;
	}
	public void setWx_mch_id(String wx_mch_id) {
		this.wx_mch_id = wx_mch_id;
	}
	public String getWx_key() {
		return wx_key;
	}
	public void setWx_key(String wx_key) {
		this.wx_key = wx_key;
	}
	public String getMp_txtName() {
		return mp_txtName;
	}
	public void setMp_txtName(String mp_txtName) {
		this.mp_txtName = mp_txtName;
	}
	public String getMp_txtContent() {
		return mp_txtContent;
	}
	public void setMp_txtContent(String mp_txtContent) {
		this.mp_txtContent = mp_txtContent;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
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
	
	public String sign(){
		Map<String, String> mp=new HashMap<String, String>();
		mp.put("merchant",merchant+"");
		mp.put("wx_appid",wx_appid);
		mp.put("wx_mch_id",wx_mch_id);
		mp.put("wx_key",wx_key);
		mp.put("mp_txtName",mp_txtName);
		mp.put("mp_txtContent",mp_txtContent);
		mp.put("valid",valid+"");
		if(StringUtils.isBlank(this.nonce_str)){
			this.nonce_str=WeixinPayUtil.nonce_str();
		}
		mp.put("nonce_str",nonce_str);
		String sign=WeixinPayUtil.sign(mp, ConstantVar.paysignkey);
		return sign;
	}
}
