package com.example.chap10_boot.repos;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.chap10_boot.entities.Album;
import com.example.chap10_boot.entities.Singer;

public interface AlbumRepository extends JpaRepository<Album, Long>{

    Iterable<Album> findBySinger(Singer singer);

    @Query(name = Album.FIND_WITH_RELEASE_DATE_GREATER_THAN)
    Iterable<Album> findWithReleaseDataGreaterThan(LocalDate rd);

    @Query("select a from Album a where a.title like %:title%")
    Iterable<Album> findByTitle(@Param("title") String t);

}
