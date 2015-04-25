package com.thoughtworks.fam.configuration;

import com.google.common.base.Strings;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * User: Zhang Shen
 * Date: 4/25/15
 * Time: 12:36 PM
 */
@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class CustomerWebMvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String externalResource = System.getProperty("externalResource");
        if (!Strings.isNullOrEmpty(externalResource)) {
            registry.addResourceHandler("/**").addResourceLocations(externalResource);
        }
        super.addResourceHandlers(registry);
    }
}
