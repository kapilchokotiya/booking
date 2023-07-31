package com.booking.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//This annotation indicates that the class is a configuration
// class and contains bean definitions and other configuration settings.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user_profile_image/**")
                .addResourceLocations("file:user_profile_image/");
    }
}

