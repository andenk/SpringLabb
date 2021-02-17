package com.example.demo;
// i application propeties vid uppstart
// spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
