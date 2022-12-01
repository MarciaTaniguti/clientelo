package br.com.alura.clientelo.api.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoForm(
	@Length(min = 2)
	@NotBlank
	String nome,
	@Positive
	@NotNull
	BigDecimal preco,
	String descricao,
	@PositiveOrZero
	@NotNull
	Long quantidadeEstoque,
	@NotNull
	Long idCategoria
){}
