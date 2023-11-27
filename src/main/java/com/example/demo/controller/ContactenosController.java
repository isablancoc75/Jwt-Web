package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContactenosDto;
import com.example.demo.entity.Contactenos;
import com.example.demo.repository.ContactenosRepository;
import com.example.demo.service.ContactenosService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactenosController {

    @Autowired
    private ContactenosService contactenosService;

    @Autowired
    private ContactenosRepository contactenosRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Contactenos>> list() {
        List<Contactenos> list = contactenosService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/contactenos")
    public ResponseEntity<Contactenos> create(@RequestBody Contactenos contactenos) {
        Contactenos newInstance = contactenosRepository.save(contactenos);
        return new ResponseEntity<>(newInstance, HttpStatus.CREATED);
    }
    /*
    @GetMapping
    public ResponseEntity<List<Contactenos>> getContactenosData() {
        List<Contactenos> contactenosList = (List<Contactenos>) contactenosRepository.findAll();
        return new ResponseEntity<>(contactenosList, HttpStatus.OK);
    }

    @PostMapping("/contactenos")
    public ResponseEntity<Contactenos> saveContactenosData(@RequestBody Contactenos contactenos) {
        Contactenos newInstance = contactenosRepository.save(contactenos);
        return new ResponseEntity<>(newInstance, HttpStatus.CREATED);
    }
    */

}