package com.example.demo;

import com.example.demo.dtos.SongDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @LocalServerPort
    int port=9090;

    @Autowired
    TestRestTemplate testClient;





    @Test
    void contextLoads() {
       // HttpHeaders headers = new HttpHeaders();
       // headers.add("Accept","application/xml");
       var res=  testClient.getForEntity("http://localhost:"+port+"/songs", SongDto[].class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(res.getBody().length).isGreaterThan(0);


    }

}
