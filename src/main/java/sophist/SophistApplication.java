package sophist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

// (exclude= {UserDetailsServiceAutoConfiguration.class}는 비번 암호화를 위해 
// 스프링 시큐리티 사용 시, 시큐리티 로그인 화면으로 가는거에 대한 비번이 콘솔에 안뜨게..?
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class SophistApplication {

	public static void main(String[] args) {
		SpringApplication.run(SophistApplication.class, args);
	}

}
