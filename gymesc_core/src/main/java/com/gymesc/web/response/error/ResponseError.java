package com.gymesc.web.response.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gymesc.infrastructure.translation.MessageUtils;
import com.gymesc.web.response.error.enumeration.ResponseErrorCodeEnum;

import lombok.Getter;

@Getter
public class ResponseError {

    private final LocalDateTime timestamp;

    private final HttpStatus status;

    private final int statusCode;

    private final ResponseErrorCodeEnum errorCode;

    private final String error;

    private final String message;

    private final String path;

    public ResponseError(HttpStatus status, ResponseErrorCodeEnum errorCode, String errorMessage, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.statusCode = status.value();
        this.errorCode = errorCode;
        this.error = MessageUtils.getEnumLabel(errorCode, "error");
        this.message = errorMessage;
        this.path = path;
    }

    public ResponseError(HttpStatus status, ResponseErrorCodeEnum errorCode, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.statusCode = status.value();
        this.errorCode = errorCode;
        this.error = MessageUtils.getEnumLabel(errorCode, "error");
        this.message = MessageUtils.getEnumLabel(errorCode, "defaultMessage");
        this.path = path;
    }

    public ResponseEntity<ResponseError> buildResponseEntity() {
        return new ResponseEntity<>(this, this.getStatus());
    }
}