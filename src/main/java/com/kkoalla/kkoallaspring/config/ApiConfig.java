package com.kkoalla.kkoallaspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.service-key}")
    private String serviceKey;

    public String getFullUrl(int page, int perPage) {
        return String.format("%s?serviceKey=%s&page=%d&perPage=%d&returnType=JSON",
                baseUrl, serviceKey, page, perPage);
    }
}
