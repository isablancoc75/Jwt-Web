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

import com.example.demo.entity.UsuarioAdmon;
import com.example.demo.repository.UsuarioAdmonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioAdmonTest {

    @Autowired
    private UsuarioAdmonRepository usuarioAdmonRepository;

    @Test
    @Order(1)
    public void testCreate(){

        UsuarioAdmon user = new UsuarioAdmon();
        user.setId("0001");
        user.setNombre("Jane Doe");
        user.setCorreo("doejane@mail.com");
        user.setPassword("jane001");

        usuarioAdmonRepository.save(user);

        

        UsuarioAdmon createdUser = usuarioAdmonRepository.findById(user.getId()).orElse(null);
        assertNotNull(createdUser);
        assertEquals("Jane Doe", createdUser.getNombre());
    }

    @Test
    @Order(2)
    public void testDBC() {

        UsuarioAdmon user = usuarioAdmonRepository.findById("0001").orElse(null);
        assertNotNull(user);
    }

    @Test
    @Order(4)
    public void testDelete(){

        String ID1 = "0001", ID2="0002";
        
        usuarioAdmonRepository.deleteById(ID1);
        usuarioAdmonRepository.deleteById(ID2);

        UsuarioAdmon deletedUsr1 =usuarioAdmonRepository.findById(ID1).orElse(null);
        UsuarioAdmon deletedUsr2 =usuarioAdmonRepository.findById(ID2).orElse(null);
        assertNull(deletedUsr1);
        assertNull(deletedUsr2);
        
    }


    @Test
    @Order(3)
    public void testUpdate(){


        UsuarioAdmon user = new UsuarioAdmon();
        user.setId("0002");
        user.setNombre("John Doe");
        user.setCorreo("doejohn@mail.com");
        user.setPassword("john0002");
        
        usuarioAdmonRepository.save(user);
        
        user.setNombre("Updated Name");
        user.setCorreo("updated@mail.com");
        user.setPassword("updatedPassword");

        usuarioAdmonRepository.save(user);

        UsuarioAdmon updatedUser = usuarioAdmonRepository.findById(user.getId()).orElse(null);

        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.getNombre());
        assertEquals("updated@mail.com", updatedUser.getCorreo());
        assertEquals("updatedPassword", updatedUser.getPassword());
    }

}
