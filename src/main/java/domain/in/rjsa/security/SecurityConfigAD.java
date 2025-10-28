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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Profile("WithAD")
public class SecurityConfigAD {

	@Value("${ad.domain}")
	private String AD_DOMAIN;

	@Value("${ad.url}")
	private String AD_URL;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ActiveDirectoryLdapAuthenticationProvider adProvider) throws Exception {

        // Set up authentication provider
        http.authenticationProvider(adProvider)

                // Configure authorization for specific requests
                .authorizeHttpRequests(auth -> auth
                        // Permit specific URLs and static resources
                        .requestMatchers(
                                "/login", "/login.jsp", "/logout",
                                "/static/css/fonts/untitled-font-2*", "/static/img/favicon.ico",
                                "/static/img/tds.png", "/static/assets/js/bootstrap.bundle.min.js",
                                "/static/img/IOB.png", "/static/img/KB.png",
                                "/static/js/bootstrap.min.js", "/static/js/lib/bootstrap.js",
                                "/static/js/jquery.min.js", "/static/css/font-awesome.min.css",
                                "/static/fonts/css/font-awesome.css", "/static/css/signin.css",
                                "/static/css/bootstrap.min.css", "/static/img/TOS.png",
                                "/apidownloadCertificate/**"
                        )
                        .permitAll() // Allow unrestricted access to the above URLs
                        .anyRequest().authenticated() // Require authentication for any other request
                )

                // Configure form-based login
                .formLogin(form -> form
                        .loginPage("/login.jsp") // Custom login page
                        .loginProcessingUrl("/login") // URL to process login
                        .defaultSuccessUrl("/", true) // Redirect to homepage on successful login
                        .permitAll() // Allow unrestricted access to login page
                )

                // Configure logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // Logout URL
                        .logoutSuccessUrl("/logout") // Redirect to logout page after successful logout
                        .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler()) // Custom logout success handler
                        .permitAll() // Allow unrestricted access to logout URL
                )

                // Configure exception handling for access-denied situations
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/Error.html") // Redirect to error page if access is denied
                )

                // CSRF configuration with CookieCsrfTokenRepository
                // .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .csrf(csrf -> csrf.disable()) // Disabled for now

                // Enable CORS configuration
                .cors(cors-> cors.configurationSource(corsConfigurationSource()))

                // Configure HTTP headers for security
                .headers(headers -> headers
                        // Set HTTP Strict Transport Security (HSTS) headers
                        .httpStrictTransportSecurity(hsts -> hsts
                                .maxAgeInSeconds(31536000) // Set max age to 1 year
                                .includeSubDomains(true) // Include all subdomains
                                .preload(true) // Enable HSTS preload
                        )
                        // Set same-origin policy for frame options
                        .frameOptions(frame -> frame.sameOrigin())
                        // Add header to prevent content-type sniffing
                        .addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options", "nosniff"))
                        // Set referrer policy to same-origin
                        .referrerPolicy(ref -> ref.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN))
                )

                // Configure session management
                .sessionManagement(session -> session
                        .sessionAuthenticationStrategy(sessionAuthenticationStrategy()) // Custom session authentication strategy
                        .sessionConcurrency(concurrency -> concurrency
                                .maximumSessions(1) // Only one session per user
                                .maxSessionsPreventsLogin(false) // Do not prevent new login attempts if session limit is reached
                                .sessionRegistry(sessionRegistry()) // Custom session registry
                        )
                );

        return http.build();
    }

    // CORS Configuration Source
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow cookies or authorization headers
        config.setAllowedOrigins(
                Arrays.asList("http://localhost:3000", "http://localhost:3001", "http://localhost:4173"));
        config.setAllowedMethods(Arrays.asList("GET", " POST", " PUT", "DELETE", "OPTIONS")); // Allow all HTTP methods (GET, POST)
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow all headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints
        return source;
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