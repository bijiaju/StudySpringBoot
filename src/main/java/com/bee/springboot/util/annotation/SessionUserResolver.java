/*
package com.bee.springboot.util.annotation;


i
import org.apache.catalina.security.SecurityUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * 解析解析当前参数用户
 *
 * @author weichunhe
 *//*

@Component
public class SessionUserResolver implements CustomArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SessionUser_.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        SessionUser user = SecurityUtil.getSessionUser(request);
        return user;
    }
}
*/
