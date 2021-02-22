package com.example.demo.controllers;

import com.example.demo.dtos.SongDto;
import com.example.demo.services.ServiceInterface;

import java.util.List;
import java.util.Optional;

public class TestService implements ServiceInterface {
    @Override
    public List<SongDto> all() {
        return null;
    }

    @Override
    public Optional<SongDto> getOne(Long id) {
        if (id==1) {
            return Optional.of(new SongDto(1L, "Test", 3, "Test"));
        }
            return Optional.empty();



    }

    @Override
    public SongDto create(SongDto songDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SongDto replace(Long id, SongDto songDto) {
        return null;
    }

    @Override
    public SongDto update(Long id, SongDto songDto) {
        return null;
    }

    @Override
    public SongDto update(Long id, SongArtist songArtist) {
        return null;
    }

    @Override
    public Optional<SongDto> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<SongDto> findTitle(String title) {
        return Optional.empty();
    }
}
