package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CancionDto;
import com.example.demo.dto.Mensaje;
import com.example.demo.entity.Cancion;
import com.example.demo.entity.Lista;
import com.example.demo.repository.ListaRepository;
import com.example.demo.service.CancionService;
import com.example.demo.service.ListaService;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/cancion")
@CrossOrigin(origins = "http://localhost:4200")
public class CancionController {

    @Autowired
    CancionService songService;

    @Autowired
    ListaRepository listRepository;

    @Autowired
    ListaService listService;

    @GetMapping("/lista")
    public ResponseEntity<List<Cancion>> list() {
        List<Cancion> list = songService.list();
        return new ResponseEntity<List<Cancion>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Cancion> getById(@PathVariable("id") Long id) {
        if (!songService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Cancion song = songService.getOne(id).get();
        return new ResponseEntity(song, HttpStatus.OK);
    }

    @GetMapping("/detailnombre/nombre")
    public ResponseEntity<Cancion> getByNombre(@PathVariable("nombre") String nombre) {
        if (!songService.existsByNombre(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Cancion song = songService.getByNombre(nombre).get();
        return new ResponseEntity(song, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CancionDto songDto) {
        if (StringUtils.isBlank(songDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(songDto.getGenero())) {
            return new ResponseEntity(new Mensaje("El genero es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        // Obtener la lista desde la base de datos o crear una nueva
        Lista lista = listRepository.findById(songDto.getListaId()).orElseGet(() -> new Lista(songDto.getGenero()));

        // Crear la canción y establecer la relación
        Cancion song = new Cancion(songDto.getNombre(), songDto.getGenero(), songDto.getRating(),
                songDto.getArtista(), songDto.getAlbum());

        // Establecer la relación en ambos lados
        lista.getCanciones().add(song);
        song.setLista(lista);

        // Guardar la Canción y la Lista
        listService.save(lista);
        songService.save(song);

        return new ResponseEntity(new Mensaje("Canción creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CancionDto songDto) {
        if (!songService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(songDto.getGenero())) {
            return new ResponseEntity(new Mensaje("El genero es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(songDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Cancion song = songService.getOne(id).get();
        song.setNombre(songDto.getNombre());
        song.setArtista(songDto.getArtista());
        song.setAlbum(songDto.getAlbum());
        song.setGenero(songDto.getGenero());
        song.setRating(songDto.getRating());
        // Usa setLista para establecer la relación con la entidad Lista
        Lista lista = listService.getListaById(songDto.getListaId()).orElse(null);
        song.setLista(lista);
        songService.save(song);
        return new ResponseEntity(new Mensaje("Canción actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!songService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró la canción con el ID " + id), HttpStatus.NOT_FOUND);
        }
        Cancion cancion = songService.getOne(id).get();
        Lista listaAsociada = cancion.getLista();

        // Eliminar la canción de la lista
        if (listaAsociada != null) {
            listaAsociada.getCanciones().remove(cancion);

            // Guardar la lista actualizada
            listService.save(listaAsociada);
        }

        // Finalmente, eliminar la canción
        songService.delete(id);
        return new ResponseEntity(new Mensaje("Canción borrada"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> buscarCanciones(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "artista", required = false) String artista,
            @RequestParam(name = "genero", required = false) String genero) {

        if (nombre == null && artista == null && genero == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Cancion> canciones = songService.buscarCanciones(nombre, artista, genero);

        if (canciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }

}
