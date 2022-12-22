package com.training.videoteca.security;



/*
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {



    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("pippo")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build());
        return manager;
    }


    private static final String[] USER_MATCHER = {"/films/cerca/**", "/films/listaAll",
            "/generi/listaGeneri", "/interpreti/lista", "/films/listaFilmsId", "/films/filmcompleto/**"};
    private static final String[] ADMIN_MATCHER =
            {"/films/new", "/films/film/update",
            "/films/film/genere/update","/films/film/delete",
            "/films/film/add/interprete","/films/film/remove/interprete"};


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        (http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/accesso/test").permitAll()
                .antMatchers(USER_MATCHER).hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and())
                .httpBasic().realmName("REAME").authenticationEntryPoint(getBasicAuthEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthEntryPoint getBasicAuthEntryPoint() {
        return new AuthEntryPoint();
    }

}


 */
