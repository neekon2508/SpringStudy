package com.example.chap9_boot.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.example.chap9_boot.entities.Album;
import com.example.chap9_boot.entities.Singer;
import com.example.chap9_boot.ex.TitleTooLongException;

public interface AllService {
    Optional<Singer> findByIdWIthAlbums(Long id);

    Stream<Singer> findAllWithAlbums();

    void update(Singer singer);

    void saveSingerWithAlbums(Singer s, Set<Album> albums) throws TitleTooLongException;

    Long countSingers();
}
