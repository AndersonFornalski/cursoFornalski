package com.Fornalski.cursoFornalski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Fornalski.cursoFornalski.domain.Cidade;
import com.Fornalski.cursoFornalski.domain.Cliente;
import com.Fornalski.cursoFornalski.domain.Endereco;
import com.Fornalski.cursoFornalski.domain.enums.TipoCliente;
import com.Fornalski.cursoFornalski.dto.ClienteDTO;
import com.Fornalski.cursoFornalski.dto.ClienteNewDTO;
import com.Fornalski.cursoFornalski.repositories.ClienteRepository;
import com.Fornalski.cursoFornalski.repositories.EnderecoRepository;
import com.Fornalski.cursoFornalski.services.exceptions.DataIntegrityException;
import com.Fornalski.cursoFornalski.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					" Objeto nao encontrado! Id:" + id + ", Tipo :" + Cliente.class.getName());
		}

		return obj;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entiades relacionadas");
		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	/* Função onde vai retornar uma página de Clientes */
	public Page<Cliente> findPage(Integer Page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(Page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	/* Método auxiliar que instância uma Cliente apartir de um DTO */
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
	}
		return cli;
}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
