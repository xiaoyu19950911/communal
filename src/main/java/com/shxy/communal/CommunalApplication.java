package com.shxy.communal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.shxy.communal"})
public class CommunalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunalApplication.class, args);
    }
}
