package br.com.alura.clientelo.service;

import br.com.alura.clientelo.relatorio.Relatorio;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioService {
	private final List<Relatorio> relatorios;

	public RelatorioService(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public String gerarRelatorios() {
		return relatorios.stream().map(Relatorio::gerarRelatorio)
				.collect(Collectors.joining(" "));
	}

}
