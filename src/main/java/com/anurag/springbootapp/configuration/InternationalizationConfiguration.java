package com.anurag.springbootapp.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class InternationalizationConfiguration {
	
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver=new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

}
