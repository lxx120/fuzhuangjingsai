package com.match.common;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Base64Utils;

/** 
 * @author mengly 
 * @version 创建时间：2015年10月27日 下午6:53:16 
 * 类说明 :签名
 */

public class DSAMethod {
	 /** 
     * 用私钥对信息生成数字签名 
     *  
     * @param data 
     *            加密数据 
     * @param privateKey 
     *            私钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String sign(byte[] data, String privateKey) throws Exception {  
        // 解密由base64编码的私钥  
        byte[] keyBytes =Base64Utils.decodeFromString(privateKey);  
  
        // 构造PKCS8EncodedKeySpec对象  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");  
  
        // 取私钥匙对象  
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // 用私钥对信息生成数字签名  
        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());  
        signature.initSign(priKey);  
        signature.update(data);  
  
        return Base64Utils.encodeToString(signature.sign());  
    }  
    /** 
     * 用私钥对信息生成数字签名 
     *  
     * @param data 
     *            加密数据 
     * @param privateKey 
     *            私钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String sign(String content, String privateKey) throws Exception {  
        // 解密由base64编码的私钥  
    	byte[] data=content.getBytes("utf-8");
        byte[] keyBytes =Base64Utils.decodeFromString(privateKey);  
  
        // 构造PKCS8EncodedKeySpec对象  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");  
  
        // 取私钥匙对象  
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // 用私钥对信息生成数字签名  
        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());  
        signature.initSign(priKey);  
        signature.update(data);  
  
        return Base64Utils.encodeToString(signature.sign());  
    } 
    public static boolean verify(String content, String publicKey, String sign)  
            throws Exception {  
    	byte[] data=content.getBytes("utf-8");
        // 解密由base64编码的公钥  
        byte[] keyBytes = Base64Utils.decodeFromString(publicKey);  
  
        // 构造X509EncodedKeySpec对象  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
  
        // ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");  
  
        // 取公钥匙对象  
        PublicKey pubKey = keyFactory.generatePublic(keySpec);  
  
        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());  
        signature.initVerify(pubKey);  
        signature.update(data);  
  
        // 验证签名是否正常  
        return signature.verify(Base64Utils.decodeFromString(sign));  
    } 
    

    /** 
     * 生成密钥 
     *  
     * @param seed 
     *            种子 
     * @return 密钥对象 
     * @throws Exception 
     */  
    public static Map<String, Object> initKey(String seed) throws Exception {  
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");  
        // 初始化随机产生器  
        SecureRandom secureRandom = new SecureRandom();  
        secureRandom.setSeed(seed.getBytes());  
        keygen.initialize(1024, secureRandom);  
  
        KeyPair keys = keygen.genKeyPair();  
  
        DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();  
        DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();  
  
        Map<String, Object> map = new HashMap<String, Object>(2);  
        map.put("publicKey", publicKey);  
        map.put("privateKey", privateKey);  
  
        return map;  
    }  
}
