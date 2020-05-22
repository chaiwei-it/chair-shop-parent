package com.mood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 应用模块
 * @author chaiwei
 * @time 2017-11-25 下午08:00
 */
@SpringBootApplication
@MapperScan("com.mood.**.dao.mapper")
@EnableTransactionManagement
@EnableScheduling
public class RestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
