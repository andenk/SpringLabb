package com.example.demo.services;

import com.example.demo.controllers.SongArtist;
import com.example.demo.dtos.SongDto;
import com.example.demo.entities.Song;
import com.example.demo.mappers.SongMapps;
import com.example.demo.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongMapps songMapper;
    private SongRepository songRepository;


    public SongService(SongRepository songRepository , SongMapps songMapper) {
        this.songRepository = songRepository;
        this.songMapper=songMapper;
    }

    public List<SongDto> all() {
        System.out.println("inne");
        return songMapper.mapp(songRepository.findAll());
    }



    public Optional<SongDto> getOne(Long id){
        return songMapper.mapp(songRepository.findById(id));
    }

    public SongDto create(SongDto songDto){
        System.out.println("<<<<<<<<<<<<<<<<<"+ songDto.toString());
        if (songDto.getTitle().isEmpty())
            throw new RuntimeException();

        return songMapper.mapp(songRepository.save(songMapper.mapp(songDto)));
    }

    public void delete(Long id) {
        if (songRepository.findById(id).isPresent()){
            songRepository.deleteById(id);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);



    }

    public SongDto replace(Long id, SongDto songDto) {
        Optional<Song> song =  songRepository.findById(id);
        if (song.isPresent()){
            Song updateSong = song.get();
            updateSong.setTitle(songDto.getTitle());
            updateSong.setArtist(songDto.getArtist());
            updateSong.setSongLength(songDto.getSongLength());
           return songMapper.mapp(songRepository.save(updateSong));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);


    }

    public SongDto update(Long id, SongDto songDto) {

        Optional<Song> song =  songRepository.findById(id);
        if (song.isPresent()){

            Song updateSong = song.get();
            if (songDto.getTitle() != null ){
                updateSong.setTitle(songDto.getTitle());}
            if (songDto.getArtist() != null ){
            updateSong.setArtist(songDto.getArtist());
            }

            updateSong.setSongLength(songDto.getSongLength());
            return songMapper.mapp(songRepository.save(updateSong));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);


    }
    public SongDto update(Long id, SongArtist songArtist) {

        Optional<Song> song =  songRepository.findById(id);
        if (song.isPresent()){

            Song updateSong = song.get();

            if (songArtist.getArtist() != null ){
                updateSong.setArtist(songArtist.getArtist());
            }
            return songMapper.mapp(songRepository.save(updateSong));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);


    }

    public Optional<SongDto> find(Long id) {

       // var idLength =songMapper.mapp(songRepository.findById(id));
       // return idLength.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " Not Found"));
return songMapper.mapp(songRepository.findById(id));

    }
}
