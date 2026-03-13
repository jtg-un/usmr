package com.urms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置 - 前后端分离部署
 */
@Configuration
public class CorsConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 允许的域名（生产环境请修改为实际域名）
        config.addAllowedOriginPattern("*");

        // 允许的请求方法
        config.addAllowedMethod("*");

        // 允许的请求头
        config.addAllowedHeader("*");

        // 允许暴露的响应头
        config.addExposedHeader("*");

        // 允许携带凭证（cookies等）
        config.setAllowCredentials(true);

        // 预检请求的有效期（秒）
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 匹配所有路径
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}