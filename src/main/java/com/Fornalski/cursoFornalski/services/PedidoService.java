package com.Fornalski.cursoFornalski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Fornalski.cursoFornalski.domain.Pedido;
import com.Fornalski.cursoFornalski.repositories.PedidoRepository;
import com.Fornalski.cursoFornalski.services.exceptions.ObjectNotFoundException;

@Service 
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido find (Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException(" Objeto nao encontrado! Id:" + id
					+ ", Tipo :" + Pedido.class.getName());
		}
		
		return obj;
	}
}
