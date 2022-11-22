package br.com.alura.clientelo;

import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    public SpringDataApplication(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoria = new Categoria("Brinquedo");
        categoriaRepository.save(categoria);
    }
}
