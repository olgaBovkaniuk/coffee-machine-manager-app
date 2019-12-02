package by.pvt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("by.pvt")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/coffee-machine/add*").access("hasRole('USER') or hasRole('ADMIN')")
//                .antMatchers("/coffee-machine/add*").hasRole("USER")
//                .antMatchers("/coffee-machine/add*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/coffee-machine/add*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/coffee-machine/add*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/coffee-machine/add*").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/coffee-machine/add*").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/coffee-machine/welcome")
                .failureUrl("/login?error=true")
                .and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthenticationService();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
