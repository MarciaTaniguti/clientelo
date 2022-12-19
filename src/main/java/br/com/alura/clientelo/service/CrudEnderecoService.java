package br.com.alura.clientelo.service;

import br.com.alura.clientelo.api.mapper.EnderecoMapper;
import br.com.alura.clientelo.dto.EnderecoDto;
import br.com.alura.clientelo.orm.Endereco;
import br.com.alura.clientelo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
public class CrudEnderecoService {
	@Autowired
	private EnderecoRepository repository;
	@Autowired
	private EnderecoMapper mapper;

	@Transactional
	public EnderecoDto cadastrar(Endereco endereco) {
		repository.save(endereco);
		return mapper.toDto(endereco);
	}
}
