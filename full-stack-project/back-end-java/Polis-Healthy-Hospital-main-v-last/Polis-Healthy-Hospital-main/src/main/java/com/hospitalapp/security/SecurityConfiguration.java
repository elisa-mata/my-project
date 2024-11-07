package com.hospitalapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CorsProperties.class)
public class SecurityConfiguration {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        /**
         * The CORS configuration.
         */
        @Autowired
        private transient CorsProperties corsProperties;

        /**
         * Defines a ConfigurationSource for CORS attributes.
         * 
         * @return A CorsConfigurationSource.
         */
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            final CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(corsProperties.getAllowedOrigins());
            configuration.setAllowedMethods(corsProperties.getAllowedMethods());
            configuration.setAllowedHeaders(corsProperties.getAllowedHeaders());
            configuration.setAllowCredentials(corsProperties.getAllowCredentials());
            configuration.setExposedHeaders(corsProperties.getExposedHeaders());
            configuration.setMaxAge(corsProperties.getMaxAgeSeconds());

            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration(corsProperties.getFilterRegistrationPath(), configuration);
            return source;
        }

        @Override
        protected void configure(final HttpSecurity http) throws Exception {

            // @formatter:off
            
            http
                .cors()
                .and()
                .csrf().disable();

        }
    }
}
