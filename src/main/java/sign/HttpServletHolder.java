/**
 * 360hqb.com.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package sign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP SERVLET请求和响应持有者
 * <p>持有请求和响应的本地线程变量，请在请求进入后设置变量，在返回前清除变量</p>
 * 
 * @author ZhouJun
 * @version $Id: HttpServletHolder.java, v 0.1 2012-5-5 下午05:01:34 ZhouJun Exp $
 */
public class HttpServletHolder {

    /** 请求的本地线程变量 */
    private static ThreadLocal<HttpServletRequest> currentRequest  = new ThreadLocal<HttpServletRequest>();

    /** 响应的本地线程变量 */
    private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();

    /**
     * 设置请求和响应的本地线程变量
     * 
     * @param request HTTP SERVLET请求
     * @param response HTTP SERVLET响应
     */
    public static void set(HttpServletRequest request, HttpServletResponse response) {
        currentRequest.set(request);
        currentResponse.set(response);
    }

    /**
     * 获取当前HTTP SERVLET请求
     * 
     * @return 当前HTTP SERVLET响应
     */
    public static HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }

    /**
     * 获取当前HTTP SERVLET响应
     * 
     * @return 当前HTTP SERVLET响应
     */
    public static HttpServletResponse getCurrentResponse() {
        return currentResponse.get();
    }

    /**
     * 清除请求和响应的本地线程变量
     */
    public static void remove() {
        currentRequest.remove();
        currentResponse.remove();
    }
}
