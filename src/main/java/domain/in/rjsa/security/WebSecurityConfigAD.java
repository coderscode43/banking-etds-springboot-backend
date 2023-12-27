package domain.in.rjsa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import domain.in.rjsa.dao.impl.HibernateTokenRepositoryImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAD extends WebSecurityConfigurerAdapter {
	
	
////////////////////////////////////////////////KARNATAKA BANK///////////////////////////////////////////////	

//	@Value("${ad.domain}")
//	private String AD_DOMAIN;
//
//	@Value("${ad.url}")
//	private String AD_URL;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.formLogin().loginPage("/login.jsp").loginProcessingUrl("/login").defaultSuccessUrl("/", true).permitAll()
//				.and().authorizeRequests()
//				.antMatchers("/login", "/", "/static/**", "/static/img/favicon.ico",
//						"/static/img/tds.png","/apidownloadCertificate/**")
//				.permitAll().anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/Access_Denied")
//				.and().csrf().disable().headers().frameOptions().sameOrigin();
//
////        http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//		authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider())
//				.userDetailsService(userDetailsService());
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager() {
//		return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
//	}
//
//	@Bean
//	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN,
//				AD_URL);
//		provider.setConvertSubErrorCodesToExceptions(true);
//		provider.setUseAuthenticationRequestCredentials(true);
//		provider.setUserDetailsContextMapper(userDetailsContextMapper());
//		return provider;
//	}
//
//	@Bean
//	public UserDetailsContextMapper userDetailsContextMapper() {
//		return new CustomUserMapper();
//	}
//
//	@Bean
//	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
//		return new AuthenticationTrustResolverImpl();
//	}
	
////////////////////////////////////////////////UCO BANK and NIA///////////////////////////////////////////////	
	
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	@Autowired
	HibernateTokenRepositoryImpl tokenRepository;
	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login.jsp").loginProcessingUrl("/login").defaultSuccessUrl("/", true).permitAll()
				.and().authorizeRequests()
				.antMatchers("/login", "/", "/static/css/fonts/untitled-font-2*", "/static/img/favicon.ico",
						"/static/img/tds.png","/static/assets/**", "/static/img/UcoBank.png", "/static/js/bootstrap.min.js",
						"/static/js/lib/bootstrap.js", "/static/js/jquery.min.js", "/static/css/font-awesome.min.css",
						"/static/fonts/css/font-awesome.css", "/static/css/signin.css", "/static/css/bootstrap.min.css",
						"/static/img/TOS.png", "/apidownloadCertificate/**")
				.permitAll().anyRequest().authenticated().and().rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(tokenRepository).tokenValiditySeconds(10000).and().exceptionHandling()
				.accessDeniedPage("/Access_Denied").and().csrf().disable().headers().frameOptions().sameOrigin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authProvider);
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}


}