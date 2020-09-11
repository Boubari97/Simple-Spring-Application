package com.example.phonebook.utils;

import com.example.phonebook.services.JsonParser;
import com.example.phonebook.services.PdfBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        return new FreeMarkerViewResolver();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public PdfBuilder pdfBuilder() {
        return new PdfBuilder();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public JsonParser jsonParser() {
        return new JsonParser();
    }
}
