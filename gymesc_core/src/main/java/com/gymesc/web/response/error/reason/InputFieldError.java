package com.gymesc.web.response.error.reason;

import com.gymesc.infrastructure.translation.MessageUtils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InputFieldError {

    public InputFieldError(String field, String message) {
        this.field = MessageUtils.getMessage("field." + field);
        this.message = message;
    }

    private String field;

    private String message;

}
