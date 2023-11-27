package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UsuarioAdmon;
import com.example.demo.repository.UsuarioAdmonRepository;

@Controller
public class UsuarioAdmonController {

    @Autowired
    private UsuarioAdmonRepository uAdmonRepository;
    
    @GetMapping("/usuarioadmon")
    public String showForm(Model model) {
        model.addAttribute("usuarioadmon", new UsuarioAdmon());
        return "";
    }
    
    @PostMapping("/usuarioadmon")
    public String saveData(@ModelAttribute UsuarioAdmon usuarioadmon) {

        uAdmonRepository.save(usuarioadmon);
        return "redirect:/";
    }
    
}
