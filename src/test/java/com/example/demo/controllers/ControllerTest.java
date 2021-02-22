package com.example.demo.controllers;

import com.example.demo.services.ServiceInterface;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void testToFindOneWithID(){
        Controller controller = new Controller(new TestService());
        var song = controller.findOne(1L);
        assertThat(song.getArtist()).isEqualTo("Test");
        assertThat(song.getTitle()).isEqualTo("Test");
        assertThat(song.getId()).isEqualTo(1);
    }
    @Test
    void testFindningInvalidIDThrowsExceptions404(){

}

}