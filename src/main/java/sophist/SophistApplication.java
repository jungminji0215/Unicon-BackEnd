package sophist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// (exclude= {UserDetailsServiceAutoConfiguration.class}는 비번 암호화를 위해 
// 스프링 시큐리티 사용 시, 시큐리티 로그인 화면으로 가는거에 대한 비번이 콘솔에 안뜨게..?
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class SophistApplication {

	public static void main(String[] args) {
		SpringApplication.run(SophistApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000/*").allowCredentials(true).maxAge(3600);
			}
		};
	}
}
