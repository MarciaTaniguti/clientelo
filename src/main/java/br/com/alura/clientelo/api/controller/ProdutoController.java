package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.ProdutoForm;
import br.com.alura.clientelo.service.CrudProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/api/produtos"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {
	@Autowired
	private CrudProdutoService produtoService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProdutoForm> cadastrar(@RequestBody @Valid ProdutoForm produto) {
		ProdutoForm produtoCriado = produtoService.cadastra(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
	}
}
