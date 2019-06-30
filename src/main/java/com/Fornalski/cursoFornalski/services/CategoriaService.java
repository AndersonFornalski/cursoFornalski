package com.Fornalski.cursoFornalski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Fornalski.cursoFornalski.domain.Categoria;
import com.Fornalski.cursoFornalski.dto.CategoriaDTO;
import com.Fornalski.cursoFornalski.repositories.CategoriaRepository;
import com.Fornalski.cursoFornalski.services.exceptions.DataIntegrityException;
import com.Fornalski.cursoFornalski.services.exceptions.ObjectNotFoundException;

@Service 
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria find (Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException(" Objeto nao encontrado! Id:" + id
					+ ", Tipo :" + Categoria.class.getName());
		}		
		return obj;
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete (Integer id) {
		find(id);
		try {
			repo.delete(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
	public List<Categoria> findAll(){
		return repo.findAll();  
	}
	/*Função onde vai retornar uma página de categorias*/
	public Page<Categoria> findPage(Integer Page, Integer linesPerPage, String orderBy, String direction ){
		PageRequest pageRequest = new PageRequest(Page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	/*Método auxiliar que instância uma Categoria apartir de um DTO*/
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome()); 
	}
}










