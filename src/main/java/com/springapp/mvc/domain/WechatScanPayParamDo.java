package com.springapp.mvc.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/10 0010.
 */
public class WechatScanPayParamDo implements Serializable {

    /**
     * ����ַ���(������32λ)
     */
    private String nonce_str;
    /**
     * ǩ��
     */
    private String sign;

    /**
     * �̻�������
     */
    private String product_id;

    /**
     * �̻���
     */
    private String mch_id;

    /**
     * ���ں�appid
     */
    private String appid;

    /**
     * ʱ���
     */
    private String time_stamp;


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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
