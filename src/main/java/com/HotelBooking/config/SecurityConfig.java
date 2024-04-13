
package com.HotelBooking.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/users/addUser", "/api/v1/users/login").permitAll()
                .requestMatchers("/api/v1/countries/addCountry").hasRole("ADMIN")
                .requestMatchers("/api/v1/users/profile").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


// "/api/v1/countries/addCountry" :- this url can be access by authenticated user whose role is 'ADMIN'

/*
   1 .requestMatchers("/api/v1/countries/addCountry").hasRole("ADMIN"):-

   this url only ADMIN can be accessed... suppose mera admin ashirvad hai to sirf ashirvad is is url ko access krega
   koi dusra access nhi krega

   2  .requestMatchers("/api/v1/users/profile").hasAnyRole("USER","ADMIN"):-

   this url only ADMIN and USER can be accessed... suppose mera ADMIN ashirvad aur USER ravi hai to... Is url ko
   dono access kr skte hai
 */
