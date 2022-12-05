package br.com.alura.clientelo;

import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.repository.CategoriaRespository;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

}
