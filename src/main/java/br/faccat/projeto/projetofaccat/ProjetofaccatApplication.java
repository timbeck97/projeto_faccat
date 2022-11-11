package br.faccat.projeto.projetofaccat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProjetofaccatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetofaccatApplication.class, args);
	}
        
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            System.out.println("ALTERADO 22:13");
            return new BCryptPasswordEncoder();
        }
//         @Bean
//        public WebMvcConfigurer corsConfigurer() {
//            return new WebMvcConfigurerAdapter() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("/**").allowedOrigins("http://localhost:5500");
//                }
//            };
//        }

}
