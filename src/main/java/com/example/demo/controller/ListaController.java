package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CancionDto;
import com.example.demo.dto.ListaDto;
import com.example.demo.dto.Mensaje;
import com.example.demo.entity.Cancion;
import com.example.demo.entity.Lista;
import com.example.demo.service.CancionService;
import com.example.demo.service.ListaService;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/lista")
@CrossOrigin(origins = "http://localhost:4200")
public class ListaController {

    @Autowired
    ListaService lisService;
    @Autowired
    CancionService cancionesService;

    @GetMapping("/listas")
    public ResponseEntity<List<Lista>> list() {
        List<Lista> list = lisService.list();
        return new ResponseEntity<List<Lista>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Lista> getById(@PathVariable("id") Long id) {
        if (!lisService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Lista song = lisService.getOne(id).get();
        return new ResponseEntity(song, HttpStatus.OK);
    }

    @GetMapping("/detailgenero/genero")
    public ResponseEntity<Lista> getByNombre(@PathVariable("genero") String genero) {
        if (!lisService.existsByGenero(genero)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Lista song = lisService.getByGenero(genero).get();
        return new ResponseEntity(song, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ListaDto songDto) {
        if (StringUtils.isBlank(songDto.getGenero())) {
            return new ResponseEntity(new Mensaje("El genero es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Lista lista = new Lista();
        lista.setGenero(songDto.getGenero());

        // Asegúrate de establecer la referencia en las canciones a la nueva lista
        List<CancionDto> cancionesDto = songDto.getCanciones();
        if (cancionesDto != null) {
            List<Cancion> canciones = new ArrayList<>();
            for (CancionDto cancionDto : cancionesDto) {
                Cancion cancion = new Cancion();
                BeanUtils.copyProperties(cancionDto, cancion);
                cancion.setLista(lista);
                canciones.add(cancion);
            }
            // Actualiza la lista de canciones en la entidad Lista
            lista.setCanciones(canciones);
        }

        lisService.save(lista);
        return new ResponseEntity(new Mensaje("Genero creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ListaDto listaDto) {
        if (!lisService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(listaDto.getGenero())) {
            return new ResponseEntity(new Mensaje("El genero es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Lista lista = lisService.getOne(id).orElse(null);
        if (lista == null) {
            return new ResponseEntity(new Mensaje("No se encontró la lista con el ID " + id), HttpStatus.NOT_FOUND);
        }

        lista.setGenero(listaDto.getGenero());

        // Actualiza las canciones asociadas
        List<CancionDto> cancionesDto = listaDto.getCanciones();
        if (cancionesDto != null) {
            List<Cancion> canciones = new ArrayList<>();
            for (CancionDto cancionDto : cancionesDto) {
                if (cancionDto.getNombre() != null && cancionDto.getArtista() != null) {
                    // Si el nombre está presente, carga la instancia existente desde la base de datos
                    Cancion cancionExistente = cancionesService.getByNombreAndArtista(cancionDto.getNombre(), cancionDto.getArtista()).orElse(null);
                    if (cancionExistente != null) {
                        // Actualiza los valores de la canción existente con los nuevos valores
                        BeanUtils.copyProperties(cancionDto, cancionExistente);
                        cancionExistente.setLista(lista);
                        canciones.add(cancionExistente);
                    }
                } else {
                    // Si el ID no está presente, crea una nueva instancia
                    Cancion cancionNueva = new Cancion();
                    BeanUtils.copyProperties(cancionDto, cancionNueva);
                    cancionNueva.setLista(lista);
                    canciones.add(cancionNueva);
                }
            }
            // Actualiza la lista de canciones en la entidad Lista
            lista.setCanciones(canciones);
        }

        lisService.save(lista);
        return new ResponseEntity(new Mensaje("Genero actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!lisService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró el genero con el ID " + id), HttpStatus.NOT_FOUND);
        }
        lisService.delete(id);
        return new ResponseEntity(new Mensaje("Genero borrada"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cancionesPorLista/{id}")
    public ResponseEntity<List<Cancion>> getCancionesPorLista(@PathVariable("id") Long id) {
        List<Lista> listas = lisService.list();
        Optional<Lista> listaOpt = listas.stream().filter(lista -> lista.getId().equals(id)).findFirst();

        if (listaOpt.isPresent()) {
            List<Cancion> canciones = listaOpt.get().getCanciones();
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
