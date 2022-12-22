package br.com.alura.clientelo.infra.api.rest.produto;

import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.core.entity.produto.Produto;
import br.com.alura.clientelo.core.usecase.dto.ExibeProdutoDto;
import br.com.alura.clientelo.core.usecase.dto.ProdutoDto;
import br.com.alura.clientelo.core.usecase.produto.ProdutoMapper;
import br.com.alura.clientelo.core.usecase.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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
	private ProdutoService produtoService;
	@Autowired
	private ProdutoMapper mapper;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid CadastroProdutoForm produtoForm) {
		Produto produto = produtoService.cadastra(mapper.toModel(produtoForm));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(produto));
	}

	@GetMapping
	public ResponseEntity<Page<ExibeProdutoDto>> listaProdutos(@SortDefault(sort = "nome") @PageableDefault(size = 5) final Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.listaTodos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> buscaProduto(@PathVariable Long id) {
		Produto produto = produtoService.buscaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(produto));
	}
}
