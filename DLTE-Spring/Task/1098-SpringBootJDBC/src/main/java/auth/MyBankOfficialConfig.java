package auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyBankOfficialConfig {

    @Autowired
    MyBankOfficialsService myBankOfficialsService;
    AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.formLogin();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/transact/Sender/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transact/Receiver/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transact/Amount/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST).hasAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAnyAuthority("manager","admin");
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(myBankOfficialsService);
        authenticationManager=builder.build();
        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }
}
