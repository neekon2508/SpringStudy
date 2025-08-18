package com.example.chap9_boot.repos;

import java.util.Optional;
import java.util.stream.Stream;

import com.example.chap9_boot.entities.Singer;

public interface SingerRepo {
    Stream<Singer> findAll();
    Optional<Singer> findById(Long id);
    Optional<Singer> findByFirstNameAndLastName(String fn, String ln);
    Long countAllSingers();
    Singer save(Singer singer);
}
