package com.example.demo;

import com.example.demo.dtos.SongDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

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
        System.out.println(res);
           assertThat(res.getBody().length).isGreaterThan(0);
    }

    @Test
    void Post() {
         HttpHeaders headers = new HttpHeaders();
         headers.add("Accept","application/json");
         SongDto songDto= new SongDto(1L,"t",2,"a");
        var res=  testClient.postForEntity("http://localhost:"+port+"/songs",songDto, SongDto[].class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }
    @Test
    void deleteFromVarableTest() {
        testClient.delete("http://localhost:"+port+"/songs", "1");

    }



}
