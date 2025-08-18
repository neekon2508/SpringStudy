package com.example.chap9_boot.entities;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALBUM")
@NamedQueries({
    @NamedQuery(name=Album.FIND_ALL, query = "select a from Album a where a.singer= :singer")
})
public class Album extends AbstractEntity{

    @Serial
    private static final long serialVersionUID = 3L;

    public static final String FIND_ALL = "Album.findAll";

    @Column
    private String title;

    @Column(name= "RELEASE_DATE")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        if (this.id != null)
            return this.id.equals(album.id);
        return title.equals(album.title) && releaseDate.equals(album.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate);
    }

    @Override
    public String toString() {
       return "Album - Id: " + id + ", Singer id: "+ singer.getId()
        + ", Title: " + title + ", Release Date: " + releaseDate;
    }

    

}
