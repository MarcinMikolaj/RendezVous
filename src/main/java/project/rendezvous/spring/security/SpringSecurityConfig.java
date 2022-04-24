package project.rendezvous.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailServiceImpl;


    @Autowired
    public void setUserDetailsServiceImpl(UserDetailsServiceImpl userDetailServiceImpl){
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @Bean
    public PasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password(getPassWordEncoder().encode("user1")).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(getPassWordEncoder().encode("user2")).roles("USER");
        auth.inMemoryAuthentication().withUser("user3").password(getPassWordEncoder().encode("user3")).roles("USER");

        auth.userDetailsService(userDetailServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()

                .authorizeRequests()
                   .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                   .antMatchers("/resources/**").permitAll()
                   .antMatchers(HttpMethod.GET, "/home").permitAll()
                   .antMatchers(HttpMethod.GET, "/register").permitAll()
                   .antMatchers(HttpMethod.POST, "/register").permitAll()
                   .antMatchers(HttpMethod.GET, "/contact").permitAll()
                   .antMatchers(HttpMethod.POST, "/contact").permitAll()
                   .antMatchers(HttpMethod.POST, "/test").permitAll()
                   .antMatchers("/panel").authenticated()
                .and()
                   .formLogin().loginPage("/login").permitAll().usernameParameter("username").passwordParameter("password")
                   .failureUrl("/login-error").permitAll().defaultSuccessUrl("/panel")
                .and()
                   .logout().permitAll();
    }

}
