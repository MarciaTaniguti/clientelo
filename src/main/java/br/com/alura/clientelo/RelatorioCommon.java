package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RelatorioCommon<T, S> {

	public String formatedItemList(String titulo, List<S> itens) {
		if (itens.isEmpty()) {
			return null;
		}
		String result = "\n" + titulo + "\n" + (itens.toString().replaceAll(", ", "\n"));
		return result.replaceAll("[\\[\\]\"]", "");
	}

	public static String retornaNumeroFormatado(BigDecimal numero) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(numero.setScale(2, RoundingMode.HALF_DOWN));
	}
}
