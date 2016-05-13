package com.springapp.mvc.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/10 0010.
 */
public class WechatCreateOrderParamDo implements Serializable {

    /**
     * 随机字符串(不长于32位)
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 公众号appid
     */
    private String appid;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 终端IP
     */
    private String spbill_create_ip;

    /**
     * 交易类型
     */
    private String trade_type="MICROPAY";

    /**
     * 通知地址
     */
    private String notify_url="http://www.baidu.com";


    private Integer total_fee = 1;

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


    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

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

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }
}
