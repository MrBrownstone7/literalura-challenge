package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoMuerte;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(DatosAutor datosAutor){
        this.nombre= datosAutor.nombre();
        this.anoMuerte= datosAutor.anoMuerte();
        this.anoNacimiento= datosAutor.anoNacimiento();
    }

    @Override
    public String toString() {
        return "---------------Autor---------------\n" +
                "Nombre : '" + nombre + '\'' +
                "Año de Nacimiento : " + anoNacimiento +
                "Año de Muerte : " + anoMuerte +
                "Libros : " + libros +  "\n"+
                "----------------------------------\n\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoMuerte() {
        return anoMuerte;
    }

    public void setAnoMuerte(Integer anoMuerte) {
        this.anoMuerte = anoMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
