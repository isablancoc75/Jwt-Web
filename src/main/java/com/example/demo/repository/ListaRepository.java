package com.example.demo.repository;

import java.util.Optional;
//import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.entity.Cancion;
import com.example.demo.entity.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long>{

    Optional<Lista> findByGenero(String genero);
    boolean existsByGenero(String genero);
    Optional<Lista> findById(Long id);
}