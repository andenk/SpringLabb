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
    void contextGetFromID(){
    SongDto songDto= new SongDto(1L,"t",2,"a");
    var res=  testClient.getForEntity("http://localhost:"+port+"/songs/"+songDto.getId() ,SongDto.class);
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);


}
    @Test
    void contextGetFromParamets(){
       var res=  testClient.getForEntity("http://localhost:"+port+"/songs/find?id=1",SongDto.class);
       assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);


    }
    @Test
    void Post() {
       HttpHeaders headers = new HttpHeaders();
         headers.add("Accept","application/xml");
         SongDto songDto= new SongDto(1L,"t",2,"a");
        var res=  testClient.postForEntity("http://localhost:"+port+"/",songDto, SongDto.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }
    @Test
    void deleteFromVarableTest() {

         testClient.delete("http://localhost:"+port+"/songs/", "1");

    }

    @Test
    void put(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/xml");
        SongDto songDto= new SongDto(1L,"t",2,"a");
         testClient.put("http://localhost:9090/songs/put/",songDto,SongDto.class,"1");

    }
    @Test
    void patch(){


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/xml");
        SongDto songDto= new SongDto(1L,"t",2,"a");

        testClient.patchForObject("http://localhost:"+port+"/songs/patch/",songDto,SongDto.class,"1");
       // assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }





}
