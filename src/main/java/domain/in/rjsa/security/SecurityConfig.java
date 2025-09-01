package domain.in.rjsa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@Profile("normal")
public class SecurityConfig {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
//	@Autowired
//	HibernateTokenRepositoryImpl tokenRepository;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationProvider customAuthProvider)
			throws Exception {
		http.authenticationProvider(customAuthProvider) // ✅ Custom auth provider
				.formLogin(form -> form.loginPage("/login.jsp").loginProcessingUrl("/login")
						.defaultSuccessUrl("/", true).permitAll())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/logout", "/static/css/fonts/untitled-font-2*",
								"/static/img/favicon.ico", "/static/img/tds.png",
								"/static/assets/js/bootstrap.bundle.min.js", "/static/img/NIA.png",
								"/static/img/UCO.png", "/static/js/bootstrap.min.js", "/static/js/lib/bootstrap.js",
								"/static/js/jquery.min.js", "/static/css/font-awesome.min.css",
								"/static/fonts/css/font-awesome.css", "/static/css/signin.css",
								"/static/css/bootstrap.min.css", "/static/img/TOS.png", "/apidownloadCertificate/**",
								"/api*/**", "/index/testConnection")
						.permitAll().requestMatchers(HttpMethod.TRACE).denyAll().requestMatchers(HttpMethod.OPTIONS)
						.denyAll().requestMatchers(HttpMethod.PATCH).denyAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.accessDeniedPage("/Error.html"))
				// ✅ CSRF config with CookieCsrfTokenRepository
//				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.csrf(csrf -> csrf.disable())
				// ✅ Security headers
				.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
						.httpStrictTransportSecurity(
								hsts -> hsts.maxAgeInSeconds(31536000).includeSubDomains(true).preload(true))
						.addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy",
								"upgrade-insecure-requests; block-all-mixed-content; https: data:; unsafe-inline; unsafe-eval"))
						.addHeaderWriter(
								new ReferrerPolicyHeaderWriter(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)));

		return http.build();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

}