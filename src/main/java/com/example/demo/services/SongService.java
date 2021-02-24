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
public class SongService implements ServiceInterface {
    private final SongMapps songMapper;
    private SongRepository songRepository;


    public SongService(SongRepository songRepository , SongMapps songMapper) {
        this.songRepository = songRepository;
        this.songMapper=songMapper;
    }

    @Override
    public List<SongDto> all() {

        return songMapper.mapp(songRepository.findAll());
    }



    @Override
    public Optional<SongDto> getOne(Long id){
        return songMapper.mapp(songRepository.findById(id));
    }

    @Override
    public SongDto create(SongDto songDto){
        System.out.println("<<<<<<<<<<<<<<<<<"+ songDto.toString());
        if (songDto.getTitle().isEmpty())
            throw new RuntimeException();

        return songMapper.mapp(songRepository.save(songMapper.mapp(songDto)));
    }

    @Override
    public void delete(Long id) {
        if (songRepository.findById(id).isPresent()){
            songRepository.deleteById(id);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);



    }

    @Override
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

    @Override
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
    @Override
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

    @Override
    public Optional<SongDto> find(Long id) {

       // var idLength =songMapper.mapp(songRepository.findById(id));
       // return idLength.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " Not Found"));
return songMapper.mapp(songRepository.findById(id));

    }

    @Override
    public Optional<SongDto> findTitle(String title) {
       return songMapper.mapp(songRepository.findByTitle(title));
    }
}
