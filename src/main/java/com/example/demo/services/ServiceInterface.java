package com.example.demo.services;

import com.example.demo.controllers.SongArtist;
import com.example.demo.dtos.SongDto;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface {
    List<SongDto> all();

    Optional<SongDto> getOne(Long id);

    SongDto create(SongDto songDto);

    void delete(Long id);

    SongDto replace(Long id, SongDto songDto);

    SongDto update(Long id, SongDto songDto);

    SongDto update(Long id, SongArtist songArtist);

    Optional<SongDto> find(Long id);

    Optional<SongDto> findTitle(String title);

}
