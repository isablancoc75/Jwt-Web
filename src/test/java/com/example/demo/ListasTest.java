package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Lista;
import com.example.demo.repository.ListaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListasTest {

    @Autowired
    private ListaRepository lRepository;

    @Test
    @Order(1)
    public void testCreate(){

        Lista lista = new Lista();
        lista.setId(1234567899L);
        lista.setGenero("pop");
        
        lRepository.save(lista);

        Lista createdList = lRepository.findById(lista.getId()).orElse(null);
        assertNotNull(createdList);
        assertEquals("0001", createdList.getId());
    }

    @Test
    @Order(2)
    public void testDBC() {

        Lista lista = lRepository.findById(1234567899L).orElse(null);
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testDelete(){

        Long ID1 = 1234567899L;
        Long ID2 = 123456789L;

        lRepository.deleteById(ID1);
        lRepository.deleteById(ID2);

        Lista deletedList1 = lRepository.findById(ID1).orElse(null);
        Lista deletedList2 = lRepository.findById(ID2).orElse(null);

        assertNull(deletedList1);
        assertNull(deletedList2);
        
    }


    @Test
    @Order(3)
    public void testUpdate(){


        Lista lista = new Lista();
        lista.setId(12345678L);
        lista.setGenero("rock");
    
        lRepository.save(lista);
        
        lista.setGenero("alternative");

        lRepository.save(lista);

        Lista updatedLista = lRepository.findById(lista.getId()).orElse(null);

        assertNotNull(updatedLista);
        assertEquals("alternative", updatedLista.getGenero());
    }

}
