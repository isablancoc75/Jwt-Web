package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cancion;

import java.util.List;
import java.util.Optional;


@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

    Optional<Cancion> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    Optional<Cancion> findByArtista(String artista);
    Optional<Cancion> findByGenero(String genero);
    List<Cancion> findByListaId(Long listaId);
    Optional<Cancion> findByNombreAndArtista(String nombre, String artista);
    List<Cancion> findByNombreContainingIgnoreCase(String nombre);
    List<Cancion> findByArtistaContainingIgnoreCase(String artista);
    List<Cancion> findByGeneroContainingIgnoreCase(String genero);
    
    
}
