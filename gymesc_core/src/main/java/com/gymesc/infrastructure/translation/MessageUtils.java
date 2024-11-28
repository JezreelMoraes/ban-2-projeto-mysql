package com.gymesc.infrastructure.translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component("MessageUtils")
public class MessageUtils {

    private static MessageSource messageSource;

    public static String getMessage(String code) {
        return messageSource.getMessage(code, null, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, String defaultMessage, Object[] args) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    public static String getEnumLabel(Object enumObject, String label) {
        String code = String.format("%s.%s.%s", enumObject.getClass().getSimpleName(), enumObject, label);

        return getMessage(code);
    }

    @Autowired
    private void setMessageSource(MessageSource messageSource){
        MessageUtils.messageSource = messageSource;
    }
}
