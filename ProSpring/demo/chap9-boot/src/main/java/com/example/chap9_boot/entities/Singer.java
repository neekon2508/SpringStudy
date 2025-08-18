package com.example.chap9_boot.entities;

import java.io.Serial;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;;

@Entity
@Table(name = "SINGER")
@NamedQueries({
    @NamedQuery(name=Singer.FIND_ALL, query="select s from Singer s"),
    @NamedQuery(name=Singer.COUNT_ALL, query = "select count(s) from Singer s"),
    @NamedQuery(name=Singer.FIND_BY_FIRST_AND_LAST_NAME, query="select s from Singer s where s.firstName = :fn and s.lastName = :ln")
})
public class Singer extends AbstractEntity{
    @Serial
    private static final long serialVersionUID = 2L;

    public static final String FIND_ALL = "Singer.findAll";
    public static final String COUNT_ALL = "Singer.countAll";
    public static final String FIND_BY_FIRST_AND_LAST_NAME = "Singer.findByFirstAndLastName";

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT",
            joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Singer singer = (Singer) obj;
        if (this.id != null)
            return this.id.equals(singer.id);
        return firstName.equals(singer.firstName) && lastName.equals(singer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
         + ", Last name: " + lastName + ", Birthday: " + birthDate;
    }

    
}
