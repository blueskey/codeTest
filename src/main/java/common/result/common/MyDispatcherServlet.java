/**
 * 360hqb.com.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package common.result.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import sign.HttpServletHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 扩展的分发控制器
 * <p>
 * 提供了以下功能：
 * <ul>
 *  <li>在分发之前设置HTTP SERVLET请求和响应到本地线程变量，分发后清除</li>
 *  <li>发生异常时，尝试获取处理器，以便交给应用处理异常</li>
 *  <li>替换支持文件上传的Http请求对象</li>
 * </ul>
 * </p>
 *
 */
public class MyDispatcherServlet extends DispatcherServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = 6838675037894461030L;

    private static final Log  log              = LogFactory.getLog(MyDispatcherServlet.class);

    /**
     * @see DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
                                                                                       throws Exception {
        try {
            HttpServletHolder.set(request, response);
            super.doDispatch(request, response);
        } catch (Exception ex) {
            log.error("发生未知异常，将交给容器处理", ex);
            throw ex;
        } finally {
            HttpServletHolder.remove();
        }
    }

    /**
     * @see DispatcherServlet#checkMultipart(HttpServletRequest)
     */
    protected HttpServletRequest checkMultipart(HttpServletRequest request)
                                                                           throws MultipartException {
        HttpServletRequest processRequest = super.checkMultipart(request);
        if (processRequest != request) {
            HttpServletHolder.set(processRequest, HttpServletHolder.getCurrentResponse());
        }
        return processRequest;
    }

    /**
     * @see DispatcherServlet#processHandlerException(HttpServletRequest, HttpServletResponse, Object, Exception)
     */
    protected ModelAndView processHandlerException(HttpServletRequest request,
                                                   HttpServletResponse response, Object handler,
                                                   Exception ex) throws Exception {
        if (null == handler) {
            try {
                HandlerExecutionChain mappedHandler = getHandler(request);
                handler = (null != mappedHandler ? mappedHandler.getHandler() : null);
            } catch (Exception e) {
                log.error("获取处理器发生未知异常", e);
            }
        }
        return super.processHandlerException(request, response, handler, ex);
    }

}
