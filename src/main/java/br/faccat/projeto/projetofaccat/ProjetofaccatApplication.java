package br.faccat.projeto.projetofaccat;

import br.faccat.projeto.projetofaccat.configuration.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProjetofaccatApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ProjetofaccatApplication.class, args);
	}
        
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            System.out.println("ALTERADO 21:26 27/11/2022");
            return new BCryptPasswordEncoder();
        }


    @Override
    public void run(String... args) throws Exception {

    }
}
