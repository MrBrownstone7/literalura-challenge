package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.controller.AutorController;
import com.aluracursos.literalura.controller.LibroController;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);

    private List<Libro> libros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private LibroController libroController;
    private AutorController autorController;
    private boolean ingresaNumero = false;

    public Principal(LibroController libroC, AutorController autorC) {
        this.libroController = libroC;
        this.autorController = autorC;
    }


    public void muestraElMenu() {
        var opcion = -1;


        while (opcion != 0) {
            var menu = """
                ------------ LITERALURA ------------
                Elija la opción a través de su número:
                1 - Buscar libros
                2 - Mostrar libros registrados
                3 - Mostrar autores registrados
                4 - Mostrar autores vivos en un año específico
                5 - Mostrar libros por idioma
                0 - Salir
                """;
            System.out.println(menu);

            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        mostrarLibrosBuscados();
                        break;
                    case 3:
                        mostrarAutoresBuscados();
                        break;
                    case 4:
                        mostrarAutoresVivosPorAno();
                        break;
                    case 5:
                        mostrarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida, por favor selecciona una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una opción válida (número entero).");
                teclado.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }


    private void buscarLibro() {
        System.out.println("Ingrese el titulo del libro: ");
        libroController.obtenerLibro();
    }

    private void mostrarLibrosBuscados() {
        System.out.println(libroController.listarLibros());
    }

    private void mostrarAutoresBuscados() {
        System.out.println(autorController.listarAutores());
    }

    private void mostrarAutoresVivosPorAno() {
        System.out.println("Ingrese el año en que el autor buscado estaba vivo: ");
        Integer anioMuerte = Integer.valueOf(teclado.nextLine());
        System.out.println(autorController.listarPorAutorVivo(anioMuerte));
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros: \n" +
                "en -> English/Inglés\n" +
                "fr -> French/Francés\n" +
                "es -> Español\n" +
                "pt -> Portugués\n\n");
        String idioma = teclado.nextLine();

        System.out.println(libroController.listarLibrosPorIdioma(idioma));
    }
}
