package com.cloud.health.mainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class MainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainServiceApplication.class, args);
    }

//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//
//        return new LocalSessionFactoryBean();
//    }


}
