package fr.eni.tp.enchere;

import fr.eni.tp.enchere.bll.UtilisateurService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    protected final Log logger = LogFactory.getLog(getClass());

    private final UtilisateurService utilisateurService;

    public SecurityConfiguration(UtilisateurService personneService) {
        this.utilisateurService = personneService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(utilisateurService)
                .passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(auth->
                {
                    auth.requestMatchers(HttpMethod.GET,"/").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/error").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/css/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/images/**").permitAll();

                    // LoginController
                    auth.requestMatchers(HttpMethod.GET,"/login").permitAll();
                    // auth.requestMatchers("/register").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/register").permitAll();

                    // EncheresController
                    auth.requestMatchers(HttpMethod.GET,"/").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/enchere").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/nouvelle-vente").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/nouvelle-vente").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/vente-remportee").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/detail-vente").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/ajouter_photo").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/change-password").permitAll();

                    //UtilisateurController
                    auth.requestMatchers(HttpMethod.GET,"/profil").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/profil/sauvegarder").permitAll();

                    auth.requestMatchers(HttpMethod.GET,"/change-password").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/detail-vente").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/profil").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/").permitAll();

                    // il n'y a que l'eleveur qui peut modifier les moutons
                   auth.requestMatchers(HttpMethod.GET,"/change-password").hasAnyRole("USER");
//                    auth.requestMatchers(HttpMethod.GET,"/create").hasAnyRole("ELEVEUR");
//                    auth.requestMatchers(HttpMethod.POST,"/moutonSave").hasAnyRole("ELEVEUR");

                    auth.anyRequest().authenticated();
                })
                .csrf(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .formLogin(f ->
                        f.loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", true)  // redirige vers /home après connexion
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .permitAll())
                .build();
    }

}
