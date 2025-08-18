package com.example.chap9_boot.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.chap9_boot.entities.Album;
import com.example.chap9_boot.entities.Singer;
import com.example.chap9_boot.ex.TitleTooLongException;
import com.example.chap9_boot.repos.AlbumRepo;
import com.example.chap9_boot.repos.SingerRepo;

@Service
@Transactional
public class AllServiceImpl implements AllService{

    private final SingerRepo singerRepo;
    
    private final AlbumRepo albumRepo;

    public AllServiceImpl(SingerRepo singerRepo, AlbumRepo albumRepo) {
        this.singerRepo = singerRepo;
        this.albumRepo = albumRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Singer> findByIdWIthAlbums(Long id) {
        var singerOpt = singerRepo.findById(id);
        singerOpt.ifPresent(s->s.setAlbums(albumRepo.findBySinger(s).collect(Collectors.toSet())));
        return singerOpt;
    }

    @Transactional(readOnly = true)
    @Override
    public Stream<Singer> findAllWithAlbums() {
        var singers = singerRepo.findAll();
        return singers.peek(s->s.setAlbums(albumRepo.findBySinger(s).collect(Collectors.toSet())));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(Singer singer) {
        singerRepo.save(singer);
    }

    @Transactional(rollbackFor = TitleTooLongException.class)
    @Override
    public void saveSingerWithAlbums(Singer s, Set<Album> albums) throws TitleTooLongException {
        var singer = singerRepo.save(s);
        if (singer != null) {
            albums.forEach(a-> a.setSinger(singer));
            albumRepo.save(albums);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Long countSingers() {
        return singerRepo.countAllSingers();
    }

}
