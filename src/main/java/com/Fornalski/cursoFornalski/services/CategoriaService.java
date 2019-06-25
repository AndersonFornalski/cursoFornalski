package com.Fornalski.cursoFornalski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Fornalski.cursoFornalski.domain.Categoria;
import com.Fornalski.cursoFornalski.repositories.CategoriaRepository;
import com.Fornalski.cursoFornalski.services.exceptions.ObjectNotFoundException;

@Service 
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException(" Objeto nao encontrado! Id:" + id
					+ ", Tipo :" + Categoria.class.getName());
		}
		
		return obj;
	}
}
