package com.patbaumgartner.prometheus.grafana.demo;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PrometheusGrafanaDemoApplication {

    @Autowired
    private DataSource dataSource;

    // @Counted needs an Aspect, @Timed not. More information see here: https://github.com/spring-projects/spring-boot/issues/17260
    @Bean
    CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }

    public static void main(String[] args) {
        SpringApplication.run(PrometheusGrafanaDemoApplication.class, args);
    }
}
