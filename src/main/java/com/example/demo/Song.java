package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="songs")
public class Song {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Song(Long id,  String title, int songLength, String artist) {
        this.id = id;
        this.title = title;
        this.songLength = songLength;
        this.artist = artist;
    }

    private String title;
    private int songLength;
    private String artist;

    public Song() {

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

    public void setSongLength(int songLengt) {
        this.songLength = songLengt;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
