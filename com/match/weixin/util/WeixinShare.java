package com.match.weixin.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WeixinShare {

    /**
     * 调用微信JS接口的临时票据
     * @return
     */
    public static String getJsApiTicket() {
        String access_token=WechatUtils.access_token();
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        assert access_token != null;
        String requestUrl = url.replace("ACCESS_TOKEN", access_token);
        // 发起GET请求获取凭证
        String res=Wxhtmethod.ssl_get(requestUrl);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(res).getAsJsonObject();
        if (object.get("errcode").getAsInt() == 0){
            System.out.println("———————— 获取 ticket : " + object.get("ticket").getAsString());
            return object.get("ticket").getAsString();
        }else {
            return null;
        }

    }

    /**
     * 签名
     * @param url
     * @return
     */
    public static Map<String, String> sign(String url) {
        Map<String, String> ret = new HashMap<>();
        String jsapi_ticket = getJsApiTicket();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}
