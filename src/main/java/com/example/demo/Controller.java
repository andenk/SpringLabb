package com.example.demo;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;
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
    public Controller(SongRepository songRepository) {
        this.songRepository = songRepository;
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
        System.out.println("---------------------"+songDto.toString());
        return songService.create(songDto);

    }








    @GetMapping("/songs/title/{title}")
    public Song byTitle(@PathVariable String title) {
        var result = songRepository.findByTitle(title);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "title " + title + " Not Found"));
    }


        
    @PutMapping("/songs/put/{id}")
    public Song saveResource(@RequestBody Song newSong,
                                          @PathVariable("id") Long id) {
        return songRepository.findById(id)
                .map(song -> {
                    song.setTitle(newSong.getTitle());
                    song.setSongLength(newSong.getSongLength());
                    song.setArtist(newSong.getArtist());
                    return songRepository.save(song);
                })
                .orElseGet(() -> {
                    newSong.setId(id);
                    return songRepository.save(newSong);
                });




    }

    @DeleteMapping("/songs/delete/{id}")
    void deleteSong(@PathVariable Long id){
        songRepository.deleteById(id);

    }

    @PatchMapping("/patch")
    public @ResponseBody ResponseEntity<String> patch() {
        return new ResponseEntity<String>("PATCH Response", HttpStatus.OK);
    }

    @GetMapping("/songs/find")
    @ResponseBody
    public Song getParameters(@RequestParam Long id) {
        var idLength =songRepository.findById(id);
        return idLength.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " Not Found"));

    }
}
