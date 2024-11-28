package com.gymesc.infrastructure.translation;

import java.util.Locale;
import java.util.regex.Pattern;

import org.hibernate.validator.internal.engine.MessageInterpolatorContext;
import org.hibernate.validator.messageinterpolation.AbstractMessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import jakarta.validation.MessageInterpolator;

public class RecursiveLocaleContextMessageInterpolator extends AbstractMessageInterpolator {

    private static final Pattern PATTERN_FIELD_PLACEHOLDER = Pattern.compile("\\{field\\}");

    private final MessageInterpolator interpolator;

    public RecursiveLocaleContextMessageInterpolator(ResourceBundleMessageInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    @Override
    public String interpolate(MessageInterpolator.Context context, Locale locale, String message) {
        MessageInterpolatorContext messageInterpolatorContext = (MessageInterpolatorContext) context;

        message = this.interpolator.interpolate(message, messageInterpolatorContext, locale);

        String fieldName = messageInterpolatorContext.getPropertyPath().toString();
        String fieldLabel = MessageUtils.getMessage("field." + fieldName);

        message = message.replaceAll(PATTERN_FIELD_PLACEHOLDER.pattern(), fieldLabel);

        return message;
    }
}