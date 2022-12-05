package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.api.form.ExibeProdutoForm;
import br.com.alura.clientelo.service.CrudProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/api/produtos"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {
	@Autowired
	private CrudProdutoService produtoService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<CadastroProdutoForm> cadastrar(@RequestBody @Valid CadastroProdutoForm produto) {
		CadastroProdutoForm produtoCriado = produtoService.cadastra(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
	}

	@GetMapping
	@Cacheable(value = "listaProdutos")
	public ResponseEntity<Page<ExibeProdutoForm>> listaProdutos(@SortDefault(sort = "nome") @PageableDefault(size = 5) final Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.listaTodos(pageable));
	}
}
