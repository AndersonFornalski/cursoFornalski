package com.Fornalski.cursoFornalski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Fornalski.cursoFornalski.domain.Cliente;
import com.Fornalski.cursoFornalski.dto.ClienteDTO;
import com.Fornalski.cursoFornalski.repositories.ClienteRepository;
import com.Fornalski.cursoFornalski.services.exceptions.DataIntegrityException;
import com.Fornalski.cursoFornalski.services.exceptions.ObjectNotFoundException;

@Service 
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente find (Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException(" Objeto nao encontrado! Id:" + id
					+ ", Tipo :" + Cliente.class.getName());
		}
		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete (Integer id) {
		find(id);
		try {
			repo.delete(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entiades relacionadas");
		}
		
	}
	public List<Cliente> findAll(){
		return repo.findAll();  
	}
	/*Função onde vai retornar uma página de Clientes*/
	public Page<Cliente> findPage(Integer Page, Integer linesPerPage, String orderBy, String direction ){
		PageRequest pageRequest = new PageRequest(Page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	/*Método auxiliar que instância uma Cliente apartir de um DTO*/
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}
}











