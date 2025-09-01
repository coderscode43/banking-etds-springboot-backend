package domain.in.rjsa.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@Profile("ad")
public class SecurityConfigAD {

	@Value("${ad.domain}")
	private String AD_DOMAIN;

	@Value("${ad.url}")
	private String AD_URL;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			ActiveDirectoryLdapAuthenticationProvider adProvider) throws Exception {

		http.authenticationProvider(adProvider)

				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/login.jsp", "/logout", "/static/css/fonts/untitled-font-2*",
								"/static/img/favicon.ico", "/static/img/tds.png",
								"/static/assets/js/bootstrap.bundle.min.js", "/static/img/IOB.png",
								"/static/img/KB.png", "/static/js/bootstrap.min.js", "/static/js/lib/bootstrap.js",
								"/static/js/jquery.min.js", "/static/css/font-awesome.min.css",
								"/static/fonts/css/font-awesome.css", "/static/css/signin.css",
								"/static/css/bootstrap.min.css", "/static/img/TOS.png", "/apidownloadCertificate/**")
						.permitAll().anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login.jsp").loginProcessingUrl("/login")
						.defaultSuccessUrl("/", true).permitAll())

				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/logout")
						.logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler()).permitAll())

				.exceptionHandling(ex -> ex.accessDeniedPage("/Error.html"))

//				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.csrf(csrf -> csrf.disable())

				.headers(headers -> headers
						.httpStrictTransportSecurity(
								hsts -> hsts.maxAgeInSeconds(31536000).includeSubDomains(true).preload(true))
						.frameOptions(frame -> frame.sameOrigin())
						.addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options", "nosniff"))
						.referrerPolicy(ref -> ref.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)))

				.sessionManagement(session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
						.sessionConcurrency(concurrency -> concurrency.maximumSessions(1)
								.maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry())));

		return http.build();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public ConcurrentSessionControlAuthenticationStrategy sessionAuthenticationStrategy() {
		return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
	}

	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN,
				AD_URL);
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		provider.setUserDetailsContextMapper(userDetailsContextMapper());
		return provider;
	}

	@Bean
	public UserDetailsContextMapper userDetailsContextMapper() {
		return new CustomUserMapper();
	}

}