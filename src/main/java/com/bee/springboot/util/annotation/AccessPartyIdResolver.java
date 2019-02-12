package com.zx.sms.sale.resolver;


import com.zx.sms.sale.resolver.annotation.AccessPartyId;
import com.zx.sms.security.bean.SessionUser;
import com.zx.sms.security.util.SecurityUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 解析接入方编号参数
 *
 * @author weichunhe
 */
@Component
public class AccessPartyIdResolver implements CustomArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AccessPartyId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Class clazz = parameter.getParameterType();
        String data = webRequest.getParameter(parameter.getParameterName());
        if (!Integer.class.equals(clazz)) {
            ArgumentResolverException.throwException("接入方编号必须是Integer类型");
        }
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        SessionUser user = SecurityUtil.getSessionUser(request);
        //优先返回关联的接入方
        if (user.getAccessPartyId() != null) {
            return user.getAccessPartyId();
        }
        if (!user.isAdmin()) {
            if (user.getAccessPartyId() == null) {
                ArgumentResolverException.throwException("非管理员用户必须关联接入方");
            } else {
                return user.getAccessPartyId();
            }
        }
        if (!StringUtils.isEmpty(data)) {
            return Integer.valueOf(data);
        }
        return null;
    }
}
