package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cancion;
import com.example.demo.repository.CancionRepository;

@Service
@Transactional
public class CancionService {

    @Autowired
    CancionRepository songRepository;

    public List<Cancion> list(){
        return songRepository.findAll();
    }
    public Optional<Cancion> getOne(Long id){
        return songRepository.findById(id);
    }
    public Optional<Cancion> getByNombre(String nombre){
        return songRepository.findByNombre(nombre);
    }
    public Optional<Cancion>getByNombreAndArtista(String nombre, String artista){
        return songRepository.findByNombreAndArtista(nombre, artista);
    }
    public void save (Cancion cancion){
        songRepository.save(cancion);
    }
    public void delete(Long id) {
        Optional<Cancion> cancionOptional = songRepository.findById(id);
        if (cancionOptional.isPresent()) {
            Cancion cancion = cancionOptional.get();

            // Elimina la canción sin afectar la lista
            songRepository.delete(cancion);
        }
    }
    public boolean existsById(Long id){
        return songRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return songRepository.existsByNombre(nombre);
    }
    public List<Cancion> getCancionesByListaId(Long listaId) {
        return songRepository.findByListaId(listaId);
    }

    public List<Cancion> buscarCanciones(String nombre, String artista, String genero) {
        if (nombre != null) {
            return songRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (artista != null) {
            return songRepository.findByArtistaContainingIgnoreCase(artista);
        } else if (genero != null) {
            return songRepository.findByGeneroContainingIgnoreCase(genero);
        } else {
            return songRepository.findAll();
        }
    }
    
}
