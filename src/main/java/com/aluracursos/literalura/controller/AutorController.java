package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.dto.AutorDTO;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AutorController {

    @Autowired
    private AutorService service;

    public List<AutorDTO> listarAutores(){
        return service.listarAutores();
    }

    public List<AutorDTO> listarPorAutorVivo(Integer anoMuerte){
        return service.listarPorAutorVivo(anoMuerte);
    }
}
