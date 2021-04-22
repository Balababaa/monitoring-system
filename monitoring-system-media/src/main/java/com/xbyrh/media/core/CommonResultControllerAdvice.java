package com.xbyrh.media.core;

import com.xbyrh.repo.model.support.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created on 2021/4/12.
 *
 * @author chenxinhui
 */
@ControllerAdvice
public class CommonResultControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
         Class<? extends HttpMessageConverter<?>> converterType) {
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    
    public final Object beforeBodyWrite( Object body,
         MethodParameter returnType,
         MediaType contentType,
         Class<? extends HttpMessageConverter<?>> converterType,
         ServerHttpRequest request,
         ServerHttpResponse response) {
        MappingJacksonValue container = getOrCreateContainer(body);
        // The contain body will never be null
        beforeBodyWriteInternal(container, contentType, returnType, request, response);
        return container;
    }

    private MappingJacksonValue getOrCreateContainer(Object body) {
        return body instanceof MappingJacksonValue ? (MappingJacksonValue) body :
            new MappingJacksonValue(body);
    }

    private void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,
        MediaType contentType,
        MethodParameter returnType,
        ServerHttpRequest request,
        ServerHttpResponse response) {

        Object returnBody = bodyContainer.getValue();

        if (returnBody instanceof BaseResponse) {
            return;
        }

        BaseResponse<?> baseResponse = BaseResponse.ok(returnBody);
        bodyContainer.setValue(baseResponse);
    }

}
