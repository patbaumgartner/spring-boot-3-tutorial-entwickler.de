package com.fortytwotalents.tutorial.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;

@SpringBootTest
public class ContextAndEnvironmentTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Environment environment;

    @Test
    public void shouldPrintAllSpringBeans(){
        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @TestConfiguration
    public static class TestConfig{

        @Bean
        public String example(){
            return "example";
        }
    }

}
