package com.randy.masterspring.config;

import com.randy.masterspring.date.USLocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;

/**
 * Created by randy on 2017/7/17.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatterForFieldType(LocalDate.class,new USLocalDateFormatter());
    }
}
