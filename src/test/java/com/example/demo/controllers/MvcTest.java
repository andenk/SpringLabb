package com.example.demo.controllers;

import com.example.demo.dtos.SongDto;
import com.example.demo.entities.Song;
import com.example.demo.services.ServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/*
    @MockBean
    ServiceInterface serviceInterface;
Elr
@Import(TestService.class)
    @Autowired
    ServiceInterface serviceInterface;
 */
@WebMvcTest(Controller.class)
@Import(TestService.class)
public class MvcTest {


    // Autowired för att implementera sina eget Interface för metoder som ska köras
    @Autowired
    ServiceInterface serviceInterface;
    // mockito skapar det som behövs av metoder Måste veta vad den ska ha tillbacka
    @MockBean
    ServiceInterface serviceInterfaceMockBean;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void callingWithUrlSongShouldReturnAllSongsAsJson() throws Exception{
        Mockito.when(serviceInterfaceMockBean.all()).thenReturn(List.of(new SongDto(1L,"",4,"")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/songs")
               .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }

}
