package com.xbyrh.web.core;

import com.xbyrh.common.enums.ResponseEnum;
import com.xbyrh.common.exception.BaseException;
import com.xbyrh.web.model.support.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Exception handler of controller.
 *
 * @author johnniang
 */
@RestControllerAdvice(value = {"com.xbyrh.web.controller"})
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public BaseResponse<?> handleDataIntegrityViolationException(
        DataIntegrityViolationException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        if (e.getCause() instanceof ConstraintViolationException) {
            baseResponse = handleBaseException(e.getCause());
        }
        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());
        baseResponse.setMsg("字段验证错误，请完善后重试！");
        return baseResponse;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse<?> handleMissingServletRequestParameterException(
        MissingServletRequestParameterException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);

        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());
        baseResponse.setMsg(
            String.format("请求字段缺失, 类型为 %s，名称为 %s", e.getParameterType(), e.getParameterName()));
        return baseResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> handleConstraintViolationException(ConstraintViolationException e) {
        BaseResponse<String> baseResponse = handleBaseException(e);

        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());
        baseResponse.setMsg("字段验证错误，请完善后重试！");
//        baseResponse.setData(ValidationUtils.mapWithValidError(e.getConstraintViolations()));
        return baseResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        BaseResponse<String> baseResponse = handleBaseException(e);

        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());
        baseResponse.setMsg("字段验证错误，请完善后重试！");
//        Map<String, String> errMap =
//            ValidationUtils.mapWithFieldError(e.getBindingResult().getFieldErrors());
//        baseResponse.setData(errMap);
        return baseResponse;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse<?> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {

        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());

        return baseResponse;
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public BaseResponse<?> handleHttpMediaTypeNotAcceptableException(
        HttpMediaTypeNotAcceptableException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());

        return baseResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse<?> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setCode(ResponseEnum.BAD_REQUEST.getCode());
        baseResponse.setMsg("缺失请求主体");
        return baseResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse<?> handleNoHandlerFoundException(NoHandlerFoundException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);

        HttpStatus status = HttpStatus.BAD_GATEWAY;
        baseResponse.setCode(status.value());
        baseResponse.setMsg(status.getReasonPhrase());
        return baseResponse;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseResponse<?> handleUploadSizeExceededException(MaxUploadSizeExceededException e) {
        BaseResponse<String> response = handleBaseException(e);

        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMsg("当前请求超出最大限制：" + e.getMaxUploadSize() + " bytes");
        return response;
    }

    @ExceptionHandler(BaseException.class)
    public BaseResponse<String> handleHaloException(BaseException e) {
        BaseResponse<String> baseResponse = handleBaseException(e);

        baseResponse.setCode(e.responseEnum().getCode());
        baseResponse.setData(e.getMessage());
        baseResponse.setMsg(e.getMessage());

        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> handleGlobalException(Exception e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        baseResponse.setCode(status.value());
        baseResponse.setMsg(status.getReasonPhrase());
        return baseResponse;
    }

    private BaseResponse<String> handleBaseException(Throwable t) {
        Assert.notNull(t, "Throwable must not be null");

        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setData(t.getMessage());

        log.error("Captured an exception:", t);

        return baseResponse;
    }

}

