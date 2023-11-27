package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UsuarioAdmon;

@Repository
public interface UsuarioAdmonRepository extends JpaRepository<UsuarioAdmon,String>{

    UsuarioAdmon findByCorreo(String correo);
    
}
