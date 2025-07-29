package com.aluracursos.literalura.dto;

import java.util.List;

public record AutorDTO(Long id,
                       String nombre,
                       Integer anoNacimiento,
                       Integer anoMuerte,
                       List<String> libroDTOS) {

    @Override
    public String toString() {
        return "---------------Autor---------------\n" +
                "Nombre : " + nombre + "\n" +
                "Año de Nacimiento : " + anoNacimiento + "\n" +
                "Año de Muerte : " + anoMuerte + "\n" +
                "Libros : " + libroDTOS +  "\n"+
                "----------------------------------\n\n";
    }
}
