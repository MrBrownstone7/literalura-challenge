package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.ResultadoBusqueda;
import com.aluracursos.literalura.repository.ILibroRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private ILibroRepository libroRepository;

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public List<LibroDTO> listarLibros(){
        return convierteDatos(libroRepository.findAll());
    }

    public List<LibroDTO> listarLibrosPorIdioma(String idioma){
        return convierteDatos(libroRepository.findByIdiomas(idioma));
    }

    public void registrarLibro(Libro libro){
            libroRepository.save(libro);
    }

    public List<LibroDTO> convierteDatos(List<Libro> libros){

        return libros.stream().map(l -> new LibroDTO(l.getId(), l.getTitulo(), l.getAutores().stream()
                .map(a -> a.getNombre())
                .collect(Collectors.toList()), l.getIdiomas(), l.getNumeroDescargas()))
                .collect(Collectors.toList());
    }

    public void obtenerLibro(){

        String busquedaLibro = teclado.nextLine();

        var busquedaLibroTratada = URLEncoder.encode(busquedaLibro, StandardCharsets.UTF_8);

        var json = consumoAPI.obtenerDatos(URL_BASE + busquedaLibroTratada);

        ResultadoBusqueda resultadoBusquedaUsuario = convierteDatos.obtenerDatos(json, ResultadoBusqueda.class);

        if (resultadoBusquedaUsuario.libros().isEmpty()){
            System.out.println("Libro no encontrado.");
        }else {
            //Si encontramos libros obtenemos el primer resultado
            DatosLibro libroUsuario = resultadoBusquedaUsuario.libros().get(0);
            //Tratamos los datos
            Libro libro = new Libro(libroUsuario);
            //Guardamos el nuevo libro en la base de datos mediante nuestro repository
            try {
                registrarLibro(libro);

                System.out.println("Libro registrado exitosamente: " + libro.getTitulo());

                System.out.println("---------------------------------------------");

                System.out.println(libro);
            } catch (DataIntegrityViolationException e){

                if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException){
                    System.out.println("El libro: "+ libro.getTitulo() + " ya ha sido registrado.");
                } else {
                    System.out.println("Error al registrar el libro" + e.getMessage());
                }
            }catch (Exception e){
                System.out.println("Error inesperado al intentar registrar el libro" +e.getMessage());
            }



        }

    }


}
