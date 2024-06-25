package com.digi.bxmd.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.setAllowedOriginPatterns(mutableListOf("*"))
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Specify allowed methods
        config.allowedHeaders = mutableListOf("*")  // Allow all headers
        config.allowCredentials = true  // Allow credentials
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}