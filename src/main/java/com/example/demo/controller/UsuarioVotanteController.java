package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Usuariovotante;
import com.example.demo.repository.UsuariovotanteRepository;

@Controller
public class UsuarioVotanteController {

    @Autowired
    private UsuariovotanteRepository uRepository;
    
    @GetMapping("/usuariovotante")
    public String showForm(Model model) {
        model.addAttribute("usuariovotante", new Usuariovotante());
        return "";
    }
    
    @PostMapping("/usuariovotante")
    public String saveData(@ModelAttribute Usuariovotante usuariovotante) {

        uRepository.save(usuariovotante);
        return "redirect:/";
    }
    
}
