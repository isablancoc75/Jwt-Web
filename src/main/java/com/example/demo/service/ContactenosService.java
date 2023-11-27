package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contactenos;
import com.example.demo.repository.ContactenosRepository;

@Service
@Transactional
public class ContactenosService {

    @Autowired
    private ContactenosRepository contactenosRepository;

    public List<Contactenos> list() {
        return contactenosRepository.findAll();
    }

    public Contactenos save(Contactenos contactenos) {
        return contactenosRepository.save(contactenos);//
    }
    
}
