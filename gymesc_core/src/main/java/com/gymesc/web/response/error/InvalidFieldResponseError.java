package com.gymesc.web.response.error;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.gymesc.web.response.error.enumeration.ResponseErrorCodeEnum;
import com.gymesc.web.response.error.reason.InputFieldError;

import lombok.Getter;

@Getter
public class InvalidFieldResponseError extends ResponseError {

    private final List<InputFieldError> fieldErrorList;

    public InvalidFieldResponseError(List<InputFieldError> fieldErrorList, String path) {
        super(HttpStatus.BAD_REQUEST, ResponseErrorCodeEnum.INVALID_FIELD_VALUE, path);
        this.fieldErrorList = fieldErrorList;
    }
}
