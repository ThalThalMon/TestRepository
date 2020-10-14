package com.ideanet.admin.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ideanet.admin.service.serviceImpl.UserSecurityService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private static final String SALT = "salt";//Protected this word
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
	}
	
	private static final String[] PUBLIC_MATCHERS = {
			"/webjars/**",
			"/css/**",
			"/js/**",
			"/images/**",
			"/",
			"/error/**/*",
			"/console/**"	
	};
	
	
	private static final String[] ADMIN_MATCHERS = {
			"/adminHome",
			"/user",
			"/index?logout",
			"/appointment",
			"/enableUser/**",
			"/disableUser/**"
			
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Permit All Public Matching Request
	     http
	           .authorizeRequests().
	           antMatchers(ADMIN_MATCHERS).hasRole("ADMIN").
	           antMatchers(PUBLIC_MATCHERS).permitAll().
	           anyRequest().authenticated();
	     
	     http
	           .csrf().disable().cors().disable()
	           .formLogin().failureUrl("/index?error").defaultSuccessUrl("/adminHome").loginPage("/index").permitAll()
	           .and()
	           .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
	           .and()
	           .rememberMe();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
		
	}

}
