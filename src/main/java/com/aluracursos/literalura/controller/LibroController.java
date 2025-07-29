package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LibroController {
    @Autowired
    private LibroService service;

    public void obtenerLibro(){
        service.obtenerLibro();
    }

    public List<LibroDTO> listarLibros(){
        return service.listarLibros();
    }

    public List<LibroDTO> listarLibrosPorIdioma(String idioma){
        return service.listarLibrosPorIdioma(idioma);
    }


}
