package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song create(@RequestBody Song song){
       return songRepository.save(song);

    }
    @PutMapping("/songs/put/{id}")
    public Song saveResource(@RequestBody Song newSong,
                                          @PathVariable("id") Long id) {
        return songRepository.findById(id)
                .map(song -> {
                    song.setTitle(newSong.getTitle());
                    song.setSongLength(newSong.getSongLength());
                    return songRepository.save(song);
                })
                .orElseGet(() -> {
                    newSong.setId(id);
                    return songRepository.save(newSong);
                });




    }

  /*  @PatchMapping("/songs/patch/{id}")
    public ResponseEntity<?> partialUpdateName(
            @RequestBody SongResourceTitleOnly partialUpdate, @PathVariable("id") String id) {

        return songRepository.save(partialUpdate, id);
         ResponseEntity.ok("resource address updated");
    }
*/

}
