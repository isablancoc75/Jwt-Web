package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lista;
import com.example.demo.repository.ListaRepository;

@Service
@Transactional
public class ListaService {

    @Autowired
    ListaRepository lisRepository;

    public List<Lista> list(){
        return lisRepository.findAll();
    }
    public Optional<Lista> getOne(Long id){
        return lisRepository.findById(id);
    }
    public Optional<Lista> getByGenero(String genero){
        return lisRepository.findByGenero(genero);
    }
    public void save (Lista listas){
        lisRepository.save(listas);
    }
    public void delete(Long id){
        lisRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return lisRepository.existsById(id);
    }
    public boolean existsByGenero(String genero){
        return lisRepository.existsByGenero(genero);
    }
    public Optional<Lista> getListaById(Long id) {
        return lisRepository.findById(id);//
    }
    
}
