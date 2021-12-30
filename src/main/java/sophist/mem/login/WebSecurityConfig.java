package sophist.mem.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 스프링 시큐리티 - 암호화를 위해 사용함
// cors
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   @Bean
   public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.cors().and()
         .csrf().disable()
         .formLogin().disable()
         .headers().frameOptions().disable();
   }
}