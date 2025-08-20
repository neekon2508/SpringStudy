package com.example.chap10_boot.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.chap10_boot.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long>{

    Iterable<Singer> findByFirstName(String firstName);
    Iterable<Singer> findByFirstNameAndLastName(String firstName, String lastName);
    @Query(name = Singer.FIND_ALL_WITH_ALBUMS)
    Iterable<Singer> findAllWithAlbums();
    @Modifying(clearAutomatically = true)
    @Query("update Singer s set s.firstName = ?1 where s.id = ?2")
    int setFirstNameFor(String firstName, Long id);
    

}
