package br.com.alura.clientelo.orm;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "USUARIO")
public record Usuario (
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id,
	@Email
	@NotBlank
	String email,
	@NotBlank
	String senha) {

}
