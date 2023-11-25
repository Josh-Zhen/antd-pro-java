package com.antd.antdprojava.common.utils;

import com.antd.antdprojava.common.exception.BusinessException;
import com.antd.antdprojava.common.exception.enums.SystemErrorEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServlet工具类，获取当前request和response
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 17:50
 */
public class HttpServletUtils {

    /**
     * 获取当前请求的request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(SystemErrorEnum.OBJECT_EMPTY);
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(SystemErrorEnum.OBJECT_EMPTY);
        } else {
            return requestAttributes.getResponse();
        }
    }
}
