package com.example.chap10_boot.service;

import java.util.stream.Stream;

import com.example.chap10_boot.entities.Singer;

public interface SingerService {
    Stream<Singer> findAll();

    Stream<Singer> findAllWithAlbums();

    Stream<Singer> findByFirstName(String firstName);

    Stream<Singer> findByFirstNameAndLastName(String firstName, String lastName);

    Singer updateFirstName(String firstName, Long id);

}
