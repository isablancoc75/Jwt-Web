package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "lista")
public class Lista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String genero;

    @JsonIdentityReference(alwaysAsId = true)
     @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
     private List<Cancion> canciones = new ArrayList<>();


    public Lista(){

    }
    

    public Lista(String genero) {
        this.genero = genero;
        //this.canciones = canciones;
    }


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setGenero(String genre){
        this.genero = genre;
    }

    public String getGenero(){
        return this.genero;
    }


    public List<Cancion> getCanciones() {
        return canciones;
    }


    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

}
