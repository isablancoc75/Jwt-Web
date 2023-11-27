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

import com.example.demo.entity.Cancion;
import com.example.demo.repository.CancionRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CancionTest {

    @Autowired
    private CancionRepository cRepository;

    @Test
    @Order(1)
    public void testCreate(){

        Cancion can = new Cancion(null, null, 0, null, null);
        //can.setId("0001");
        can.setNombre("Does");
        can.setAlbum("Janes");
        can.setArtista("Jane Doe");
        can.setGenero("Doe");
        can.setRating(1);

        cRepository.save(can);

        Cancion createdSong = cRepository.findById(can.getId()).orElse(null);
        assertNotNull(createdSong);
        assertEquals("Does", createdSong.getNombre());
    }

    @Test
    @Order(2)
    public void testDBC() {

        Cancion can = cRepository.findById(1234567899L).orElse(null);
        assertNotNull(can);
    }

    @Test
    @Order(4)
    public void testDelete(){

        Long ID1 = 123456789L;
        Long ID2 = 123456788L;

        cRepository.deleteById(ID1);
        cRepository.deleteById(ID2);

        Cancion deletedCan1 =cRepository.findById(ID1).orElse(null);
        Cancion deletedCan2 =cRepository.findById(ID2).orElse(null);

        assertNull(deletedCan1);
        assertNull(deletedCan2);
        
    }


    @Test
    @Order(3)
    public void testUpdate(){


        Cancion can = new Cancion(null, null, 0, null, null);
        //can.setId("0002");
        can.setNombre("Does2");
        can.setArtista("John Doe");
        can.setAlbum("Johns");
        can.setGenero("John");
        can.setRating(0);
    
        cRepository.save(can);
        
        can.setNombre("Updated Name");
        can.setGenero("Johnes");
        can.setRating(3);

        cRepository.save(can);

        Cancion updatedCan = cRepository.findById(can.getId()).orElse(null);

        assertNotNull(updatedCan);
        assertEquals("Updated Name", updatedCan.getNombre());
        assertEquals("Johnes", updatedCan.getGenero());
        assertEquals(3, updatedCan.getRating());
    }

}
