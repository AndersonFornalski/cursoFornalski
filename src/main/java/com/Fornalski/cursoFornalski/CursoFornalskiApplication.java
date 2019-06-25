package com.Fornalski.cursoFornalski;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Fornalski.cursoFornalski.domain.Categoria;
import com.Fornalski.cursoFornalski.domain.Cidade;
import com.Fornalski.cursoFornalski.domain.Estado;
import com.Fornalski.cursoFornalski.domain.Produto;
import com.Fornalski.cursoFornalski.repositories.CategoriaRepository;
import com.Fornalski.cursoFornalski.repositories.CidadeRepository;
import com.Fornalski.cursoFornalski.repositories.EstadoRepository;
import com.Fornalski.cursoFornalski.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoFornalskiApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository; 

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoFornalskiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
/*RELACIONAMENTO DE CATEGORIAS E PRODUTOS*/
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto (null,"Computador", 2000.00);
		Produto p2 = new Produto (null,"Impressora", 800.00);
		Produto p3 = new Produto (null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		
		
/*RELACIONAMENTO DE ESTADOS E CIDADES*/		
		Estado est1 = new Estado (null, "São Paulo");
		Estado est2 = new Estado (null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est2);
		Cidade c2 = new Cidade(null,"São Paulo", est1);
		Cidade c3 = new Cidade(null,"Campinas", est1);	
		
		/*Macete para adicionar na lista*/		
		est1.getCidades().addAll(Arrays.asList(c2,c3));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
	}

	
	
}
