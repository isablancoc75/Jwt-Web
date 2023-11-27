package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "contactenos"
)
public class Contactenos {

    @Id
    private String nombre;

    private String apellido;
    private String fecha;
    private int edad;

    public Contactenos(){
        this.nombre = null;
        this.apellido = null;
        this.fecha = null;
        this.edad = 0;
    }

    public Contactenos(String nom, String lastn, String dat, int age){
        this.nombre = nom;
        this.apellido = lastn;
        this.fecha = dat;
        this.edad = age;
    }

    public void setNombre(String nom){
        this.nombre = nom;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setApellido(String lastn){
        this.apellido= lastn;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getFecha(){
        return this.fecha;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public int getEdad(){
        return this.edad;
    }

    public void setValores(String nombre, String apellido, String fecha, int edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.edad = edad;
    }
    
}
