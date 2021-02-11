package biz.uoray.cucp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/top").hasRole("ADMIN").anyRequest().authenticated();
		http.formLogin().loginPage("/cucp/login").loginProcessingUrl("/sign_in").permitAll()
				.usernameParameter("username")
				.passwordParameter("password")
				.successForwardUrl("/top")
				.failureUrl("/cucp/login?error")
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/cucp/login?logout")
				.permitAll();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.authoritiesByUsernameQuery("SELECT username, m.authorities_name FROM authorities a " +
						"INNER JOIN authorities_main m ON a.authority_id = m.id AND a.username = ?");
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}