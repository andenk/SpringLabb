package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/*
Gamla Vägen

@Controller
@Rest

 */
@RestController
public class Controller {

    private SongRepository songRepository;

    @Autowired
    public Controller(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/songs")
    public List<Song> all() {
        return songRepository.findAll();
    }

    @GetMapping("/songs/title/{title}")
    public Song title(@PathVariable String title) {
        var result = songRepository.findByTitle(title);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "title " + title + " Not Found"));
    }

    @GetMapping("/songs/{id}")
    public Song one(@PathVariable Long id) {

        var result = songRepository.findById(id);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "id " + id + " Not Found"));

    }
    //HttpEntity<byte[]> requestEntity  För att få tillbacka hela bodyn som bit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song create(@RequestBody Song song){
       return songRepository.save(song);

    }


}
