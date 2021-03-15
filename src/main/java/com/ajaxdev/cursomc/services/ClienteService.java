package com.ajaxdev.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajaxdev.cursomc.domain.Cliente;
import com.ajaxdev.cursomc.repositories.ClienteRepository;
import com.ajaxdev.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo ;
	
	public Cliente find(Integer id) {
		 Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
		 "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		} 

	
}
