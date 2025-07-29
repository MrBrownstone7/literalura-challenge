package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Autor;

import java.util.List;

public record LibroDTO(Long id,
                       String titulo,
                       List<String> autores,
                       List<String> idiomas,
                       Integer numeroDescargas) {
    @Override
    public String toString() {
        return "---------------Libro---------------\n" +
                "Titulo : " + titulo + '\n' +
                "Autor/es : " + autores + '\n' +
                "Idiomas : " + idiomas + '\n' +
                "Numero de Descargas : " + numeroDescargas + "\n"+
                "----------------------------------\n\n";
    }
}
