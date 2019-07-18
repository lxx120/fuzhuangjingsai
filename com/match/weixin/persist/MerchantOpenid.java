package com.match.weixin.persist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.match.common.ConstantVar;
import com.match.weixin.util.WeixinPayUtil;


public class MerchantOpenid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	private long merchant;
	private String openid;
	private boolean valid;
	private String realName;//真实姓名
	private String nonce_str;  // 类似于微信的uuid随机字符串
	private String sign;//  签名，主要是为了防止别人随意入侵数据库篡改数据
	public long getMerchant() {
		return merchant;
	}
	public void setMerchant(long merchant) {
		this.merchant = merchant;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
		mp.put("openid",openid);
		mp.put("valid",valid+"");
		mp.put("realName",realName);
		if(StringUtils.isBlank(this.nonce_str)){
			this.nonce_str=WeixinPayUtil.nonce_str();
		}
		mp.put("nonce_str",nonce_str);
		String sign=WeixinPayUtil.sign(mp, ConstantVar.paysignkey);
		return sign;
	}
	
}
