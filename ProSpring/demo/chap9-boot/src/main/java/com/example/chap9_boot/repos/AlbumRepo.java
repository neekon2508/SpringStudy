package com.example.chap9_boot.repos;

import java.util.Set;
import java.util.stream.Stream;

import com.example.chap9_boot.entities.Album;
import com.example.chap9_boot.entities.Singer;
import com.example.chap9_boot.ex.TitleTooLongException;

public interface AlbumRepo {
    Stream<Album> findBySinger(Singer singer);

    Set<Album> save(Set<Album> albums) throws TitleTooLongException;

    Album save(Album album) throws TitleTooLongException;
}
