package br.com.alura.clientelo.infra.api.rest;

import org.apache.coyote.Response;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CacheController {
	@GetMapping("aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM")
	@CacheEvict(value = "RelatorioVendasPorCategoria", allEntries = true)
	public ResponseEntity<?> cacheEvictRelatorioVendasPorCategoria() {
		return ResponseEntity.ok().build();
	}
}
