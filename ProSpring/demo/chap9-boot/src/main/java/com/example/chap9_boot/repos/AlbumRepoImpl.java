package com.example.chap9_boot.repos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.chap9_boot.entities.Album;
import com.example.chap9_boot.entities.Singer;
import com.example.chap9_boot.ex.TitleTooLongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AlbumRepoImpl implements AlbumRepo{

    @PersistenceContext
    private EntityManager em;

    @Value("#{jpaProperties.get('hibernate.jdbc.batch_size')}")
    private int batchSize;

    @Override
    public Stream<Album> findBySinger(Singer singer) {
        return em.createNamedQuery(Album.FIND_ALL, Album.class)
            .setParameter("singer", singer)
            .getResultList().stream();
    }

    @Override
    public Set<Album> save(Set<Album> albums) throws TitleTooLongException {
        final Set<Album> savedAlbums = new HashSet<>();
        int i = 0;
        for (Album a: albums) {
            savedAlbums.add(save(a));
            i++;
            if (i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        return savedAlbums;
    }

    @Override
    public Album save(Album album) throws TitleTooLongException {
        if ( 50 < album.getTitle().length())
            throw new TitleTooLongException("Title " + album.getTitle() + " too long!");
        if (album.getId() == null) {
            em.persist(album);
            return album;
        } else 
            return em.merge(album);
    }

}
