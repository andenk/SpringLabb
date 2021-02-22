package com.example.demo.controllers;

import com.example.demo.entities.Song;
import com.example.demo.dtos.SongDto;
import com.example.demo.repository.SongRepository;
import com.example.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class Controller {

    private SongService songService;

    private SongRepository songRepository;

    @Autowired
    public Controller(SongService songService) {
        this.songService = songService;
    }



    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/songs")
    public List<SongDto> all() {
        return songService.all();
    }

    @GetMapping("/songs/{id}")
    public SongDto findOne(@PathVariable Long id) {

        var result = songService.getOne(id);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "id " + id + " Not Found"));

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongDto create(@RequestBody SongDto songDto){
        return songService.create(songDto);

    }
    @DeleteMapping("/songs/{id}")
    void deleteSong(@PathVariable Long id){
        songService.delete(id);
    }
    @PutMapping("/songs/put/{id}")
    public SongDto replace(@RequestBody SongDto songDto,
                             @PathVariable("id") Long id) {
       return songService.replace(id,songDto);

    }

    @PatchMapping("/songs/patch/{id}")
    public SongDto update(@RequestBody SongDto songDto,
                           @PathVariable("id") Long id) {
        return songService.update(id,songDto);

    }
    @PatchMapping("/songs/patch/artist/{id}")
    public SongDto update(@RequestBody SongArtist SongArtist,
                          @PathVariable("id") Long id) {
        return songService.update(id,SongArtist);

    }


    @GetMapping("/songs/find")
    @ResponseBody
    public SongDto getParameters(@RequestParam Long id) {

        var result = songService.find(id);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "id " + id + " Not Found"));

    }



    @GetMapping("/songs/title/{title}")
    public Song byTitle(@PathVariable String title) {
        var result = songRepository.findByTitle(title);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "title " + title + " Not Found"));
    }


        







}
