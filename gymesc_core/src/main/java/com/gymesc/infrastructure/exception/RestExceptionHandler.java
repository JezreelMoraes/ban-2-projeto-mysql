package com.gymesc.infrastructure.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gymesc.infrastructure.log.LogUtils;
import com.gymesc.infrastructure.translation.MessageUtils;
import com.gymesc.web.response.error.InvalidFieldResponseError;
import com.gymesc.web.response.error.ResponseError;
import com.gymesc.web.response.error.enumeration.ResponseErrorCodeEnum;
import com.gymesc.web.response.error.reason.InputFieldError;
import com.gymesc.web.security.auth.UserHelper;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> handleBusinessException(BusinessException businessException, HttpServletRequest request) {
        ResponseError responseError = new ResponseError(
            HttpStatus.BAD_REQUEST,
            ResponseErrorCodeEnum.BUSINESS_ERROR,
            businessException.getMessage(),
            request.getRequestURI()
        );

        return responseError.buildResponseEntity();
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseError> handleUnauthorizedException(ForbiddenException forbiddenException, HttpServletRequest request) {
        LogUtils.warn(String.format(
            "Usuário: [%d] tentou acessar: [%s] de forma indevida",
            UserHelper.getUserId(), request.getRequestURI()
        ), forbiddenException);

        ResponseError responseError = new ResponseError(
            HttpStatus.FORBIDDEN,
            ResponseErrorCodeEnum.UNAUTHORIZED_ACTION,
            forbiddenException.getMessage(),
            request.getRequestURI()
        );

        return responseError.buildResponseEntity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGeneralException(Exception exception, HttpServletRequest request) {
        String errorMessage = String.format(
            "Ocorreu um erro inexperado na requisição [%s] do Usuário: [%d]",
            request.getRequestURI(), UserHelper.getUserId()
        );

        LogUtils.error(errorMessage, exception);

        ResponseError responseError = new ResponseError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ResponseErrorCodeEnum.INTERNAL_SERVER_ERROR,
            exception.getMessage(),
            request.getRequestURI()
        );

        return responseError.buildResponseEntity();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        if (!(exception.getCause() instanceof InvalidFormatException)) {
            ResponseError responseError = new ResponseError(
                HttpStatus.BAD_REQUEST,
                ResponseErrorCodeEnum.BUSINESS_ERROR,
                request.getRequestURI()
            );

            return responseError.buildResponseEntity();
        }

        InvalidFormatException invalidFormatException = (InvalidFormatException) exception.getCause();
        JsonMappingException.Reference jsonMappingException = invalidFormatException.getPath().getFirst();

        String fieldName = jsonMappingException.getFieldName();
        String fieldLabel = MessageUtils.getMessage("field." + fieldName);

        String message;
        if (invalidFormatException.getTargetType().isEnum()) {
            message = MessageUtils.getMessage("request.invalid.field.enum.value", new Object[] { fieldLabel });
        } else {
            String invalidValue = invalidFormatException.getValue().toString();
            message = MessageUtils.getMessage("request.invalid.field.value", new Object[] { invalidValue, fieldLabel });
        }

        return new InvalidFieldResponseError(
            List.of(new InputFieldError(fieldName, message)),
            request.getRequestURI()
        ).buildResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {
        List<InputFieldError> inputFieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
            .map(error -> new InputFieldError(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        return new InvalidFieldResponseError(inputFieldErrorList, request.getRequestURI()).buildResponseEntity();
    }
}
