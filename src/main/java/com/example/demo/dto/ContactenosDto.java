package com.example.demo.dto;

import java.sql.Date;

public class ContactenosDto {

    private String apellido;
    private String fecha;
    private int edad;

    public ContactenosDto() {
    }

    public ContactenosDto(String apellido, String fecha, int edad) {
        this.apellido = apellido;
        this.fecha = fecha;
        this.edad = edad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getfecha() {
        return fecha;
    }

    public void setfecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
