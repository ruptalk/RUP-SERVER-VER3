package com.rup.rup_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${custom.path.user.user-image-show-path}")
    private String imageShowPath;

    @Value("${custom.path.user.user-images-path}")
    private String userImagePath;

    @Value("${custom.path.flower.flower-image-show-path}")
    private String flowerShowPath;

    @Value("${custom.path.flower.flower-images-path}")
    private String flowerImagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(imageShowPath)
                .addResourceLocations("file:///" + userImagePath);

        registry.addResourceHandler(flowerShowPath)
                .addResourceLocations("file:///" + flowerImagePath);

    }
}
