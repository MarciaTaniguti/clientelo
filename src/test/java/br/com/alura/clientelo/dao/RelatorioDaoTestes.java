package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.CadastraValoresDummy;
import br.com.alura.clientelo.dto.RelatorioClientesFieisDTO;
import br.com.alura.clientelo.dto.RelatorioProdutosMaisVendidosDTO;
import br.com.alura.clientelo.dto.RelatorioVendasPorCategoriaDTO;
import br.com.alura.clientelo.util.JPAUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioDaoTestes {
	private final EntityManager em = JPAUtil.getEntityManager();
	private final RelatorioDao relatorioDao = new RelatorioDao(em);

	@Test
	public void deveGerarRelatorioDeClientesFieis() {
		//Dados inputados pelo CadastraValoresDummy
		List<RelatorioClientesFieisDTO> relatorio = relatorioDao.obterClientesFieis();

		RelatorioClientesFieisDTO expectedGiovana = new RelatorioClientesFieisDTO(CadastraValoresDummy.getGiovana().getNome(),
				3L, new BigDecimal("33606.09"));
		RelatorioClientesFieisDTO expectedVinicius = new RelatorioClientesFieisDTO(CadastraValoresDummy.getVinicius().getNome(),
				2L, new BigDecimal("15740.00"));
		RelatorioClientesFieisDTO expectedAna = new RelatorioClientesFieisDTO(CadastraValoresDummy.getAna().getNome(),
				1L, new BigDecimal("7600.00"));

		List<RelatorioClientesFieisDTO> expectedRelatorio = List.of(expectedGiovana, expectedVinicius, expectedAna);

		assertEquals(relatorio, expectedRelatorio);
	}

	@Test
	public void deveGerarRelatorioDeVendasPorCategoria() {
		//Dados inputados pelo CadastraValoresDummy
		List<RelatorioVendasPorCategoriaDTO> relatorio = relatorioDao.obterVendasPorCategoria();

		RelatorioVendasPorCategoriaDTO expectedTecnologia = new RelatorioVendasPorCategoriaDTO("TECNOLOGIA", 5L, new BigDecimal("31100.00"));
		RelatorioVendasPorCategoriaDTO expectedDecoracao = new RelatorioVendasPorCategoriaDTO("DECORAÇÃO", 3L, new BigDecimal("270.00"));
		RelatorioVendasPorCategoriaDTO expectedLivro = new RelatorioVendasPorCategoriaDTO("LIVRO", 1L, new BigDecimal("102.90"));

		List<RelatorioVendasPorCategoriaDTO> expectedRelatorio = List.of(expectedTecnologia, expectedDecoracao, expectedLivro);

		assertEquals(expectedRelatorio, relatorio);
	}

	@Test
	public void deveGerarRelatorioProdutosMaisVendidos() {
		//Dados inputados pelo CadastraValoresDummy
		List<RelatorioProdutosMaisVendidosDTO> relatorio = relatorioDao.obterProdutosMaisVendidos();

		RelatorioProdutosMaisVendidosDTO iPhone = new RelatorioProdutosMaisVendidosDTO("iPhone 14", 4L, null, new BigDecimal("8000.00"));
		List<RelatorioProdutosMaisVendidosDTO> expectedRelatorio = List.of(iPhone);

		assertEquals(expectedRelatorio, relatorio);
	}
}
