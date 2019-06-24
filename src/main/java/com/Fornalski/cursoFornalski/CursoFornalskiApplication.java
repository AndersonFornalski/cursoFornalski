package com.Fornalski.cursoFornalski;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Fornalski.cursoFornalski.domain.Categoria;
import com.Fornalski.cursoFornalski.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoFornalskiApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository; 

	public static void main(String[] args) {
		SpringApplication.run(CursoFornalskiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		
	}

	
	
}
