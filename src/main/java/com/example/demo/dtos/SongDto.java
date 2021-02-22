package com.example.demo.dtos;

public class SongDto {



    private Long id;

    @Override
    public String toString() {
        return "SongDto{" +

                " title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", songLength=" + songLength +
                '}';
    }

    private String title;
    private String artist;
    private int songLength;

    public SongDto(Long id, String title, int songLength,String artist) {
        this.id=id;
        this.title = title;
        this.artist = artist;
        this.songLength = songLength;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }






}
