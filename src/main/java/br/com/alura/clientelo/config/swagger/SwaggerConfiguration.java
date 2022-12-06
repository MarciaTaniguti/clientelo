package br.com.alura.clientelo.config.swagger;

import br.com.alura.clientelo.orm.Usuario;
import io.swagger.models.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public Docket clienteloApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.alura.clientelo"))  //base packaged
				.paths(PathSelectors.ant("/**"))  //quais endpoints devem ser analisados
				.build()
				.ignoredParameterTypes(Usuario.class)  //ignora as urls que trabalham com usuário (segurança por causa da senha)
				.globalOperationParameters(Arrays.asList(  //paramêtros globais (em todos os end points)
						new ParameterBuilder()
								.name("Authorization")
								.defaultValue("Header para token JWT")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(false) //nem todos os endpoints precisam dele
								.build()));
	}
}
