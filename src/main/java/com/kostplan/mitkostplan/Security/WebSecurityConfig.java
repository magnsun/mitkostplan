package com.kostplan.mitkostplan.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // configuration indikerer at klassen kan blive brugt af spring IoC container som en kilde af definationer
@EnableWebSecurity //
public class WebSecurityConfig {

    //bliver brugt til at hente bruger infomationer
    private final UserDetailsService userDetailsService;

    //kontruktør injection af UserDetailService
    public WebSecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    // dette er sikkerheds filteret for aplikationen
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authz -> authz
                        // dette er de path som er tilgængelige uden at være authenticated(logged in)
                        .requestMatchers("/static/**","/js/**","/images/**", "/img/**", "/", "/forside", "/login", "/login/create", "/login/createUser", "/forside.css", "/loginStyle.css", "createStyle.css").permitAll()
                        //alle andre path kræver login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        //dette er stien til login siden
                        .loginPage("/login")
                        //stien til login processen hvor der bliver tjekket login informationer
                        .loginProcessingUrl("/login")
                        //standard url efter login er korrekt
                        .defaultSuccessUrl("/recipes", true)
                        //Url'en til hvis login informationerne er forkerte
                        .failureUrl("/login?error=true")
                        //giver alle adgang til login siden
                        .permitAll()
                )
                .logout(logout -> logout
                        //stien til at logge ud
                        .logoutUrl("/main/logout")
                        //stien hvis log out sker uden problemer
                        .logoutSuccessUrl("/")
                        // sletter JSESSIONID cookies når en bruger logger ud
                        .deleteCookies("JSESSIONID")
                        //hvis en bruger logger ud bliver alle tidllaser og informationer bliver fjernet indtil de logger ind igen
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        //giver alle adgang til at logge ud
                        .permitAll()
                )
                //definere authenticationProvider
                .authenticationProvider(authenticationProvider());
        return http.build();
    }

    // definere at password encoder bliver brugt. Her bruger vi BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //definere hvad der bliver brugt til authentication. Her bruger vi DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Sætter userDetailsService som er bruger informationer til authenticationProvider
        authProvider.setUserDetailsService(userDetailsService);
        //sætter password encoderen til authenticationProvider
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
