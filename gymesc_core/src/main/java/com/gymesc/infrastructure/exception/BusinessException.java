package com.gymesc.infrastructure.exception;

import org.apache.coyote.BadRequestException;

import com.gymesc.infrastructure.translation.MessageUtils;

public class BusinessException extends BadRequestException {

    public BusinessException(String message) {
        super(MessageUtils.getMessage(message));
    }
}