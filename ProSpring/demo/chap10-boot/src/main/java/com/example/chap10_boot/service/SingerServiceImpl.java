package com.example.chap10_boot.service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Streams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chap10_boot.entities.Singer;
import com.example.chap10_boot.repos.SingerRepository;

@Service
@Transactional
public class SingerServiceImpl implements SingerService{

    private final SingerRepository singerRepository;
    
    
    public SingerServiceImpl(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public Stream<Singer> findAll() {
        return StreamSupport.stream(singerRepository.findAll().spliterator(), false);
    }

    @Transactional(readOnly = true)
    @Override
    public Stream<Singer> findAllWithAlbums() {
        return StreamSupport.stream(singerRepository.findAllWithAlbums().spliterator(), false);
    }
    @Transactional(readOnly = true)
    @Override
    public Stream<Singer> findByFirstName(String firstName) {
        return StreamSupport.stream(singerRepository.findByFirstName(firstName).spliterator(), false);
    }

    @Transactional(readOnly = true)
    @Override
    public Stream<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return StreamSupport.stream(singerRepository.findByFirstNameAndLastName(firstName, lastName).spliterator(), false);
    }

    @Override
    public Singer updateFirstName(String firstName, Long id) {
        singerRepository.findById(id).ifPresent(s->singerRepository.setFirstNameFor(firstName, id));
        return singerRepository.findById(id).orElse(null);
    }
    
}
