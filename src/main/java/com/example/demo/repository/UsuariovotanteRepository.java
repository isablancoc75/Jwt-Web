package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuariovotante;

@Repository
public interface UsuariovotanteRepository extends JpaRepository<Usuariovotante,String>{

    Usuariovotante findByCorreo(String correo);

}