package com.springapp.mvc.util.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xujianer
 * Date: 11/24/11
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String get(String uri) {
        //构造HttpClient的实例
        //创建GET方法的实例
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(uri);
        HttpEntity entity = null;
        try {
            // 发送请求，返回响应
            HttpResponse response = httpClient.execute(request);
            entity = response.getEntity();
            //处理内容
            if (null != entity) {
                String entityStr = EntityUtils.toString(entity, "UTF-8");
                logger.info("HttpClientPostResult-----------" + entityStr);
                return entityStr;
            }
            return "";
        } catch (ClientProtocolException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            logger.error("发生致命的异常，可能是协议不对或者返回的内容有问题 ", e);
            return "";
        } catch (IOException e) {
            //发生网络异常
            logger.warn("发生网络异常 ", e);
            return "";
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public static String post(String uri, List<NameValuePair> formParams) {
        //构造HttpClient的实例
        //创建POST方法的实例
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(uri);
        UrlEncodedFormEntity urlEncodedFormEntity;
        HttpEntity entity = null;
        try {
            urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "utf-8"); //
            request.setEntity(urlEncodedFormEntity);
            // 发送请求，返回响应
            HttpResponse response = httpClient.execute(request);
            entity = response.getEntity();
            //处理内容
            if (null != entity) {
                String entityStr = EntityUtils.toString(entity);
                return entityStr;
            }
            return "";
        } catch (ClientProtocolException e) {
            logger.error("", e);
            return "";
        } catch (UnsupportedEncodingException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            logger.error("", e);
            return "";
        } catch (IOException e) {
            //发生网络异常
            logger.error("", e);
            return "";
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}
