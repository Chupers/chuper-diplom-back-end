package com.chuper.diplom.security;

import com.chuper.diplom.service.UserFacadeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final UserFacadeService userService;
    private final UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfiguration(UserFacadeService userService, UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userService = userService;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),this.userService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),this.userService))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/account/create").permitAll()
                .antMatchers(HttpMethod.POST,"/accommodation/init").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.GET,"/account/active").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.GET,"/accommodation/active").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.POST,"/accommodation/updateBasicInfo").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.PUT,"/accommodation/loadPhotoByIdGoogle").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.POST,"/accommodation/searchBySubString").permitAll()
                .antMatchers(HttpMethod.POST,"/accommodation/findById").permitAll()
                .antMatchers(HttpMethod.POST,"/feedback/addFeedback").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.PUT,"/accommodation/addCharacteristic").hasRole(UserRoleList.USER)
                .antMatchers(HttpMethod.POST, "/record").permitAll()
                .antMatchers(HttpMethod.POST, "/record/checkAvailability").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userPrincipalDetailsService);
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
