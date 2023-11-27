package com.example.demo.security;

import com.example.demo.entity.UsuarioAdmon;
import com.example.demo.entity.Usuariovotante;
import com.example.demo.service.UsuarioAdmonService;
import com.example.demo.service.UsuarioVotanteService;
import com.example.demo.repository.UsuarioAdmonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioAdmonRepository usuarioAdmonRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioAdmon usuarioAdmon = usuarioAdmonRepository.findByCorreo(correo)
                .orElseThrow(()-> new UsernameNotFoundException("ese usuario no existe"));
        return UsuarioAdmon.build(usuarioAdmon);
    }
}*/