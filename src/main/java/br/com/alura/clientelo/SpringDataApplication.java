package br.com.alura.clientelo;

import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.repository.CategoriaRespository;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCategoriaService categoriaService;

    public SpringDataApplication(CrudCategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(categoriaService.visualizar());
    }
}
