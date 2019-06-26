package com.Fornalski.cursoFornalski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Fornalski.cursoFornalski.domain.Cliente;
import com.Fornalski.cursoFornalski.repositories.ClienteRepository;
import com.Fornalski.cursoFornalski.services.exceptions.ObjectNotFoundException;

@Service 
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException(" Objeto nao encontrado! Id:" + id
					+ ", Tipo :" + Cliente.class.getName());
		}
		
		return obj;
	}
}
