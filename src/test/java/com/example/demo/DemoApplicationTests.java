package com.example.demo;

import com.example.demo.dtos.SongDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;




    @Test
    void contextLoads() {

        initializeDatabase();
        var result=  testClient.getForEntity("http://localhost:"+port+"/songs/", SongDto[].class);
       assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(result);
        assertThat(result.getBody().length).isGreaterThan(0);
    }
    @Test
    void contextGetFromID(){
        initializeDatabase();
   // SongDto songDto= new SongDto(1L,"t",2,"a");
    var result=  testClient.getForEntity("http://localhost:"+port+"/songs/1" ,SongDto.class);
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getTitle()).isEqualTo("t");
        assertThat(result.getBody().getArtist()).isEqualTo("a");

}
    private void initializeDatabase() {
        SongDto songDto = new SongDto(1L, "test", 3, "artist");
        SongDto songDto1 = new SongDto(2L, "t", 2, "a");
        testClient.postForEntity("http://localhost:" + port + "/songs", songDto, SongDto.class);
        testClient.postForEntity("http://localhost:" + port + "/songs", songDto1, SongDto.class);
    }

    @Test
    void contextGetFromParamets(){
       var res=  testClient.getForEntity("http://localhost:"+port+"/songs/find?id=1",SongDto.class);
       assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);


    }
    @Test
    void Post() {
      // HttpHeaders headers = new HttpHeaders();
    //     headers.add("Accept","application/xml");
         SongDto songDto= new SongDto(1L,"t",2,"apost");
        var res=  testClient.postForEntity("http://localhost:"+port+"/",songDto, SongDto.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var resultGet = testClient.getForEntity("http://localhost:" + port + "/songs/" + songDto.getId(),SongDto.class);
        assertThat(resultGet.getBody().getArtist()).isEqualTo("apost");


    }
    @Test
    void deleteFromVarableTest() {
        SongDto songDto= new SongDto(1L,"t",2,"apost");
        testClient.postForEntity("http://localhost:"+port+"/songs", songDto, SongDto.class);
        testClient.delete("http://localhost:"+port+"/songs/1");
        var res=  testClient.getForEntity("http://localhost:"+port+"/songs/1",SongDto.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
    @Test
    void put() {


        SongDto songDtoCreate= new SongDto(1L,"test",2,"artist");

        testClient.postForEntity("http://localhost:"+port+"/songs", songDtoCreate, SongDto.class);


        SongDto songDto= new SongDto(1L,"t",2,"a");

        testClient.put("http://localhost:"+port+"/songs/1",songDto,SongDto.class);

        var res=  testClient.getForEntity("http://localhost:"+port+"/songs/1",SongDto.class);

            assertThat(res.getBody().getTitle()).isEqualTo("t");
            assertThat(res.getBody().getArtist()).isEqualTo("a");



    }


    @Test
    void patch(){

        SongDto songDtoCreate= new SongDto(1L,"test",2,"test");

        testClient.postForEntity("http://localhost:"+port+"/songs", songDtoCreate, SongDto.class);

        SongDto songDto= new SongDto(1L,"test",2,"testpatch");

        testClient.patchForObject("http://localhost:9090/songs/1",songDto,SongDto.class);
        var res=  testClient.getForEntity("http://localhost:"+port+"/songs/1",SongDto.class);


        assertThat(res.getBody().getArtist()).isEqualTo("testpatch");


    }





}
