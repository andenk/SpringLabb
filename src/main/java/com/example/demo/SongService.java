package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    private SongRepository songRepository;


    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongDto> all() {
        System.out.println("inne");
        return mapp(songRepository.findAll());
    }



    public Optional<SongDto> getOne(Long id){
        return mapp(songRepository.findById(id));
    }

    public SongDto create(SongDto songDto){
        System.out.println("<<<<<<<<<<<<<<<<<"+ songDto.toString());
        if (songDto.getTitle().isEmpty())
            throw new RuntimeException();

        return mapp(songRepository.save(mapp(songDto)));
    }

    public SongDto mapp(Song song) {

        return new SongDto(song.getId(), song.getTitle(),song.getSongLength(), song.getArtist());
    }

    public Song mapp(SongDto songDto){

        return new Song(songDto.getId(),songDto.getTitle(), songDto.getSongLength(),songDto.getArtist());
    }
    public Optional<SongDto> mapp(Optional<Song> optionalSong){
       if (optionalSong.isEmpty())
           return Optional.empty();
       return Optional.of(mapp(optionalSong.get()));
    }
    private List<SongDto> mapp(List<Song> all) {
        return   all.stream()
                .map(this::mapp)
                .collect(Collectors.toList());

     /*   List<SongDto> songDtoList = new ArrayList<>();
        for (var song: all
             ) {
            songDtoList.add(mapp(song));
        }
            return songDtoList;
            */

    }
}