package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.AutorDTO;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.repository.IAutorRepository;
import com.aluracursos.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    @Autowired
    private IAutorRepository autorRepository;

    public List<AutorDTO> listarAutores(){
        return convierteDatos(autorRepository.findAll());
    }

    public List<AutorDTO> convierteDatos(List<Autor> autores){

         return autores.stream().map(a -> new AutorDTO(a.getId(), a.getNombre(), a.getAnoNacimiento(), a.getAnoMuerte(), a.getLibros().stream()
                         .map(l -> l.getTitulo())
                         .collect(Collectors.toList())))
                 .collect(Collectors.toList());
    }

    public List<AutorDTO> listarPorAutorVivo(Integer anoMuerte){
        return convierteDatos(autorRepository.encontrarAutoresVivosEnAnio(anoMuerte));
    }
}
