package com.agrotep.imp.exp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("/import-export.html");
//        registry.addViewController("/index.html").setViewName("/import-export.html");
        registry.addViewController("/hello").setViewName("hello");
//        registry.addViewController("/add-transportation-details").setViewName("add-transportation-details");
//        registry.addViewController("/import-export/list").setViewName("import-export");
        registry.addViewController("/transportation-details").setViewName("transportation-details");
//        registry.addViewController("/transportation-details/open/form").setViewName("transportation-details");
//        registry.addViewController("/index").setViewName("import-export");
        registry.addViewController("/login").setViewName("login");
    }

}