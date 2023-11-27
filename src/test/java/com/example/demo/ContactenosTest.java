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

import com.example.demo.entity.Contactenos;
import com.example.demo.repository.ContactenosRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactenosTest {

    @Autowired
    private ContactenosRepository cRepository;

    @Test
    @Order(1)
    public void testCreate(){

        Contactenos conc = new Contactenos();
        conc.setNombre("Jane");
        conc.setApellido("Doe");
        conc.setEdad(22);
        conc.setFecha("1970-01-01");
        cRepository.save(conc);

        Contactenos createdConc = cRepository.findById(conc.getNombre()).orElse(null);
        assertNotNull(createdConc);
        assertEquals("Jane", createdConc.getNombre());
    }

    @Test
    @Order(2)
    public void testDBC() {

        Contactenos conc = cRepository.findById("Jane").orElse(null);
        assertNotNull(conc);
    }

    @Test
    @Order(4)
    public void testDelete(){

        String ID1 = "Jane";
        String ID2 = "John";

        cRepository.deleteById(ID1);
        cRepository.deleteById(ID2);

        Contactenos deletedList1 = cRepository.findById(ID1).orElse(null);
        Contactenos deletedList2 = cRepository.findById(ID2).orElse(null);

        assertNull(deletedList1);
        assertNull(deletedList2);
        
    }


    @Test
    @Order(3)
    public void testUpdate(){

        Contactenos conc = new Contactenos();

        conc.setNombre("John");
        conc.setApellido("Doe");
        conc.setEdad(23);
        conc.setFecha("1970-01-01");
        cRepository.save(conc);
        
        conc.setApellido("Pepper");
        conc.setFecha("2022-01-01");

        cRepository.save(conc);

        Contactenos updatedConc = cRepository.findById(conc.getNombre()).orElse(null);

        assertNotNull(updatedConc);
        assertEquals("Pepper", updatedConc.getApellido());
        assertEquals("2022-01-01", updatedConc.getFecha());
    }

}
