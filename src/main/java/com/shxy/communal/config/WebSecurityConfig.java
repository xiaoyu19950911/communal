package com.shxy.communal.config;

import com.shxy.communal.user.LightSwordUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/8 14:07
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new LightSwordUserDetailService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/user/adduser").permitAll()
                .antMatchers("/amchart/**",
                        "/bootstrap/**",
                        "/build/**",
                        "/css/**",
                        "/dist/**",
                        "/documentation/**",
                        "/fonts/**",
                        "/js/**",
                        "/pages/**",
                        "/plugins/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
                .and().logout().permitAll();
        http.logout().logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("root").password("root").roles("USER").and().withUser("admin").password("admin").roles("ADMIN", "USER").and().withUser("user").password("user").roles("USER");
        //auth.userDetailsService(userDetailsService());
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
