package com.gymesc.infrastructure.translation;

import java.util.Locale;

import org.apache.commons.codec.CharEncoding;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.validation.MessageInterpolator;


@Configuration
public class TranslationConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding(CharEncoding.UTF_8);
        messageSource.setCacheSeconds(3600);

        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(Locale.of("pt"));

        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.getDefault());
        return localeResolver;
    }

    @Bean
    public MessageInterpolator getMessageInterpolator(MessageSource messageSource) {
        MessageSourceResourceBundleLocator resourceBundleLocator = new MessageSourceResourceBundleLocator(messageSource);
        ResourceBundleMessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator(resourceBundleLocator);
        return new RecursiveLocaleContextMessageInterpolator(messageInterpolator);
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(MessageInterpolator messageInterpolator) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setMessageInterpolator(messageInterpolator);
        return bean;
    }
}
