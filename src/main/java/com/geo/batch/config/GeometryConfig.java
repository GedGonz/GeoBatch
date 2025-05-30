package com.geo.batch.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GeometryConfig {

    @Bean
    GeometryFactory geometryFactory() {
        return new GeometryFactory();
    }
}
