package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;

@Configuration
public class SecurityConfig {

 @Bean
 SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
  http
    .authorizeRequests(
        authorizeRequests -> authorizeRequests.anyRequest().authenticated()
    )
    .oauth2Login(
      oauth2Login -> 
      oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
    .oauth2Client(Customizer.withDefaults());
  return http.build();
 }

}
