package com.wh.demozull;

import com.wh.demozull.accessfilter.BackstageFilter;
import com.wh.demozull.accessfilter.ReceptionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class DemoZullApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoZullApplication.class, args);
    }
    @Bean
    public BackstageFilter accessFilter(){
        return new BackstageFilter();
    }
    @Bean
    public ReceptionFilter receptionFilter(){
        return new ReceptionFilter();
    }
}
