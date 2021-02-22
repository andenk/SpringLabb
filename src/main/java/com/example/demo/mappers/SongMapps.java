package com.example.demo.mappers;

import com.example.demo.dtos.SongDto;
import com.example.demo.entities.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SongMapps {
    public SongMapps() {
    }

    public SongDto mapp(Song song) {

        return new SongDto(song.getId(), song.getTitle(), song.getSongLength(), song.getArtist());
    }

    public Song mapp(SongDto songDto) {

        return new Song(songDto.getId(), songDto.getTitle(), songDto.getSongLength(), songDto.getArtist());
    }

    public Optional<SongDto> mapp(Optional<Song> optionalSong) {
        if (optionalSong.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalSong.get()));
    }

    public List<SongDto> mapp(List<Song> all) {
        return all.stream()
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