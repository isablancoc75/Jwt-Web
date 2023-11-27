package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private String genero;
    private int rating;
    private String artista;
    private String album;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

    

    public Cancion() {
    }

    public Cancion(String nombre, String genero, int rating, String artista, String album) {
        this.nombre = nombre;
        this.genero = genero;
        this.rating = rating;
        this.artista = artista;
        this.album = album;
        //this.lista = lista;
    }

    

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setNombre(String nom){
        this.nombre= nom;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setGenero(String genre){
        this.genero= genre;
    }

    public String getGenero(){
        return this.genero;
    }

    public void setRating(int rat){
        this.rating = rat;
    }

    public int getRating(){
        return this.rating;
    }

    public void setArtista(String art){
        this.artista = art;
    }

    public String getArtista(){
        return this.artista;
    }

    public void setAlbum(String alb){
        this.album = alb;
    }

    public String getAlbum(){
        return this.album;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
    
}
