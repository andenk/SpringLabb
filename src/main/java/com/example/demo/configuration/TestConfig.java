package com.example.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
@RefreshScope
@Configuration
@ConfigurationProperties(value="test")


public class TestConfig {



        public String foo = "defalt";

        public String getFoo(){
            return foo;
        }
        public void setFoo(String foo){
            this.foo=foo;
        }


    }

