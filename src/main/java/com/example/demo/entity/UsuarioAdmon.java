package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "usuarioadmon"
)
public class UsuarioAdmon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = "ID",
        nullable = false,
        columnDefinition = "text"
    )
    private String id;

    @Column(
        name="nombre",
        nullable = false,
        columnDefinition = "Text"

    )
    private String nombre;

    @Column(
        name="correo",
        nullable = false,
        columnDefinition = "Text"
    )
    private String correo;

    @Column(
        name="password",
        nullable= false,
        columnDefinition = "Text"
    )
    private String pass;

    public UsuarioAdmon (){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nom){
        this.nombre = nom;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String mail){
        this.correo = mail;
    }

    public String getPassword(){
        return pass;
    }

    public void setPassword(String password){
        this.pass= password;
    }
    

    
}
