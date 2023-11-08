package com.example.asm2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ResourceWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        // if run on linux (heroku,...)
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+ uploadPath + "/");
        // if run on window
//        registry.addResourceHandler("/uploads/**").addResourceLocations("file:/"+ uploadPath + "/");
        System.out.println(uploadPath);
    }
}
