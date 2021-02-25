package com.example.demo.controllers;

import com.example.demo.configuration.TestConfig;
import com.example.demo.dtos.SongDto;
import com.example.demo.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController()
public class Controller {

    private final ServiceInterface serviceInterface;
    private TestConfig testConfig;
   // private SongRepository songRepository;

    @Autowired
    public Controller(TestConfig testConfig, ServiceInterface serviceInterface) {
this.testConfig= testConfig;
        this.serviceInterface = serviceInterface;
    }

/*
    public Controller(TestConfig testConfig, ServiceInterface serviceInterface) {
        this.testConfig= testConfig;
        this.serviceInterface = serviceInterface;
    }
    */

    @GetMapping("/message")
    public String message() {
        return testConfig.getFoo();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/songs")
    public List<SongDto> all() {
        return serviceInterface.all();
    }

    @GetMapping("/songs/{id}")
    public SongDto findOne(@PathVariable Long id) {

        var result = serviceInterface.getOne(id);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "id " + id + " Not Found"));

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongDto create(@RequestBody SongDto songDto){
        return serviceInterface.create(songDto);

    }
    @DeleteMapping("/songs/{id}")
    void deleteSong(@PathVariable Long id){
        serviceInterface.delete(id);
    }
    @PutMapping("/songs/put/{id}")
    public SongDto replace(@RequestBody SongDto songDto,
                             @PathVariable("id") Long id) {
       return serviceInterface.replace(id,songDto);

    }

    @PatchMapping("/songs/patch/{id}")
    public SongDto update(@RequestBody SongDto songDto,
                           @PathVariable("id") Long id) {
        return serviceInterface.update(id,songDto);

    }
    @PatchMapping("/songs/patch/artist/{id}")
    public SongDto update(@RequestBody SongArtist SongArtist,
                          @PathVariable("id") Long id) {
        return serviceInterface.update(id,SongArtist);

    }


    @GetMapping("/songs/find")
    @ResponseBody
    public SongDto getParameters(@RequestParam Long id) {

        var result = serviceInterface.find(id);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "id " + id + " Not Found"));

    }



    @GetMapping("/songs/title/{title}")
    public SongDto byTitle(@PathVariable String title) {
        var result = serviceInterface.findTitle(title);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "title " + title + " Not Found"));
    }


        







}
