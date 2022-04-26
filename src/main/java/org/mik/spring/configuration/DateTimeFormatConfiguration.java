package org.mik.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeFormatConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        DateTimeFormatter formatter= DateTimeFormatter.ISO_ZONED_DATE_TIME;
        registrar.setDateFormatter(formatter);
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
}
