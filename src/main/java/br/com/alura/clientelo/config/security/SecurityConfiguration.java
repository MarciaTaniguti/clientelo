package br.com.alura.clientelo.config.security;

import br.com.alura.clientelo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Profile(value = {"prod"})
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService autenticaoService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	//Configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	//Configurações de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/categorias").permitAll()
				.antMatchers(HttpMethod.GET, "/api/produtos").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()  //somente para fins de teste
				.anyRequest().authenticated()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}

	//Configurações de recursos estáticos (js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**","/configuration/**", "/swagger-resources/**");
	}

//	Gerar senha encriptografada
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("teste123"));
//	}
}
