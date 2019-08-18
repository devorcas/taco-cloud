package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userRepositoryUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encode() {
        return new StandardPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/design", "/orders")
            .hasRole("USER")
            .antMatchers("/", "/**").access("permitAll")

        .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design")

        .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encode());

        // LDPA Authentication
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter(" (uid={0}) ")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//        .passwordCompare()
//        .passwordEncoder(new BCryptPasswordEncoder())
//        .passwordAttribute("passcode");


        // jdbc Authentication
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                // optional. if u want to change default query queries
//                .usersByUsernameQuery(
//                        " select username, password, enabled from Users " +
//                        "where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities " +
//                        "where username=?")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        // In-memmory user store
//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                .password("{noop}infiity")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("{noop}bullseye")
//                .authorities("ROLE_USER");
    }

    // jdbc Authentication
//    @Autowired
//    private DataSource dataSource;
}
