package com.example.demo.controllers;

import com.example.demo.configuration.TestConfig;
import com.example.demo.dtos.SongDto;
import com.example.demo.entities.Song;
import com.example.demo.services.ServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    @MockBean
    ServiceInterface serviceInterface;
Elr
@Import(TestService.class)
    @Autowired
    ServiceInterface serviceInterface;
 */
@WebMvcTest(Controller.class)
@Import({TestService.class,TestConfig.class})

public class MvcTest {


    // Autowired för att implementera sina eget Interface för metoder som ska köras
    @Autowired
    ServiceInterface serviceInterface;
    // mockito skapar det som behövs av metoder Måste veta vad den ska ha tillbacka
    @MockBean
    ServiceInterface serviceInterfaceMockBean;

    @Autowired
    private MockMvc mockMvc;

@Autowired
ObjectMapper jsonMapper;
    @Test
    void callingWithUrlSongShouldReturnAllSongsAsJson() throws Exception{
        Mockito.when(serviceInterfaceMockBean.all()).thenReturn(List.of(new SongDto(1L,"",4,"")));
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/songs")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }
    @Test
    void post() throws Exception {

        var songDto = new SongDto(1L,"",4,"");

        Mockito.when(serviceInterfaceMockBean.create(eq(songDto))).thenReturn(new SongDto(1L, "t", 4, "a"));

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsBytes(songDto))
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);


    }

    @Test
    public void del() throws Exception {
        mockMvc.perform(delete("/songs/1"))
                .andExpect(status().isOk());

    }







}
