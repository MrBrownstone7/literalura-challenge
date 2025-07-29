package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany( cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas = new ArrayList<>();
    private Integer numeroDescargas;



    public Libro() {
    }

    public Libro(DatosLibro dL){
        this.titulo = dL.titulo();
        this.autores= dL.autores().stream().
                map(datosAutor -> new Autor(datosAutor))
                .collect(Collectors.toList());
        this.idiomas = dL.idiomas();
        this.numeroDescargas = dL.numeroDescargas();
    }

    @Override
    public String toString() {
       List<String> autoresToString = getAutores().stream()
                .map(a -> a.getNombre())
                .collect(Collectors.toList());
        return "---------------Libro---------------\n" +
                "Titulo : '" + titulo + '\n' +
                "Autor/es : " + autoresToString + '\n' +
                "Idiomas : " + idiomas + '\n' +
                "Numero de Descargas : " + numeroDescargas + "\n"+
                "----------------------------------\n\n";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a -> a.getLibros().add(this));
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }


}
