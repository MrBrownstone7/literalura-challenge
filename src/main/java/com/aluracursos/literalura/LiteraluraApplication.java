package com.aluracursos.literalura;

import com.aluracursos.literalura.controller.AutorController;
import com.aluracursos.literalura.controller.LibroController;
import com.aluracursos.literalura.repository.IAutorRepository;
import com.aluracursos.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aluracursos.literalura.principal.Principal;

import java.sql.DriverManager;
import java.sql.SQLException;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LibroController libroC;
	@Autowired
	private AutorController autorC;
	public static void main(String[] args) {
		try {
			// Cargar el driver explícitamente
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver PostgreSQL cargado exitosamente");
			// Registrar el driver con DriverManager
			DriverManager.registerDriver(new org.postgresql.Driver());
			System.out.println("Driver PostgreSQL registrado con DriverManager");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar el driver PostgreSQL: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error al registrar el driver con DriverManager: " + e.getMessage());
		}
        SpringApplication.run(LiteraluraApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroC, autorC);
		principal.muestraElMenu();
	}
}
