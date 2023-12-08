package com.agrotep.imp.exp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("import-export");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/import-export").setViewName("import-export");
//        registry.addViewController("/index").setViewName("import-export");
        registry.addViewController("/login").setViewName("login");
    }

}