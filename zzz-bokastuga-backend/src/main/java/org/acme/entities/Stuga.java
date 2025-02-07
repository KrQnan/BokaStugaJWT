package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Stuga")
@Table(name = "STUGA")
public class Stuga
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer id;

    @Column(name = "NAMN")
    public String name;
    @Column(name = "BESKRIVNING")
    public String beskrivning;
    @Column(name = "PRIS")
    public Integer pris;
    @Column(name = "BILD")
    public String bild;
    @OneToMany(mappedBy = "stugId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Ordrar> stugId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }

    public Integer getPris() {
        return pris;
    }

    public void setPris(Integer pris) {
        this.pris = pris;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public List<Ordrar> getStugId() {
        return stugId;
    }

    public void setStugId(List<Ordrar> stugId) {
        this.stugId = stugId;
    }

    public Stuga() {
    }

    public Stuga(Integer id, String name, String beskrivning, Integer pris, String bild, List<Ordrar> stugId) {
        this.id = id;
        this.name = name;
        this.beskrivning = beskrivning;
        this.pris = pris;
        this.bild = bild;
        this.stugId = stugId;
    }
}
