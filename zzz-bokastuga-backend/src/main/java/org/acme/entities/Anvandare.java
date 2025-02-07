package org.acme.entities;

import jakarta.persistence.*;



@Entity(name = "ANVANDARE")
@Table(name = "ANVANDARE")
public class Anvandare
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer id;
    @Column(name = "NAMN")
    public String namn;
    @Column(name = "EFTERNAMN")
    public String efternamn;
    @Column(name = "EMAIL")
    public String email;
    @Column(name = "TELEFONNUMMER")
    public String telefonnummer;
    @Column(name = "ADRESS")
    public String adress;
    @Column(name = "POSTORT")
    public String postort;
    @Column(name = "POSTNUMMER")
    public String postnummer;
    @Column(name = "PASSWORD")
    public String password;
    @Column(name = "GRUPP")
    public String grupp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostort() {
        return postort;
    }

    public void setPostort(String postort) {
        this.postort = postort;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrupp() {
        return grupp;
    }

    public void setGrupp(String grupp) {
        this.grupp = grupp;
    }

    public Anvandare() {
    }

    public Anvandare(Integer id, String namn, String efternamn, String email, String telefonnummer, String adress, String postort, String postnummer, String password, String grupp) {
        this.id = id;
        this.namn = namn;
        this.efternamn = efternamn;
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.adress = adress;
        this.postort = postort;
        this.postnummer = postnummer;
        this.password = password;
        this.grupp = grupp;
    }
}
