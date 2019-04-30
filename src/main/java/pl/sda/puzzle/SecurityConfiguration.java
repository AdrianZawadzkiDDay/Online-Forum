package pl.sda.puzzle;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.puzzle.tables.User;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/client/**")
                .permitAll()


                .antMatchers(HttpMethod.POST, "/users")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/users")
                .hasRole(User.Role.ADMIN.name())

                .antMatchers(HttpMethod.POST, "/posts")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/posts")
                .permitAll()

                .antMatchers(HttpMethod.POST, "/comments")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/comments")
                .permitAll()

                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
