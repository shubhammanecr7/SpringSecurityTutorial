package com.telusko.secureapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	// below code is used to set custom username and password
	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * List<UserDetails> users = new ArrayList<>();
	 * users.add(User.withDefaultPasswordEncoder().username("navin").password("1234"
	 * ).roles("USER").build());
	 * 
	 * return new InMemoryUserDetailsManager(users); }
	 */

	// below code is used for login authentication using bCrypt
	// stored two pass in user table 1.navin-navin 2.somu-123
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	// below code is used to login using google oauth2 login feature
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .csrf().disable() .authorizeRequests().antMatchers("/login").permitAll()
	 * .anyRequest().authenticated(); //.and().httpBasic(); }
	 */
}
