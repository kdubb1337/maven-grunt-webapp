package com.kdubb.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApplicationContext context;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/privacy")
				.antMatchers("/resources/**")
				.antMatchers("/bower_components/**")
				.antMatchers("/images/**")
				.antMatchers("/app/**")
				.antMatchers("/js/**")
				.antMatchers("/css/**")
				.antMatchers("/less/**")
				.antMatchers("/fonts/**")
				.antMatchers("/views/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/signin")
                .usernameParameter("username")
                .passwordParameter("password")
				.failureUrl("/signin?param.error=bad_credentials")
			.and()
				.logout()
					.logoutUrl("/signout")
					.deleteCookies("JSESSIONID")
			.and()
				.authorizeRequests()
					.antMatchers("/admin/**", "/favicon.ico", "/resources/**", "/auth/**", "/signin/**", "/signin2/**", "/connect/**", "/signup/**").permitAll()
					.antMatchers("/**").authenticated()
			.and()
				.rememberMe()
			.and()
				// TODO bring this back? Need to find a way to pass it to the user without jsp
				.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new StandardPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}