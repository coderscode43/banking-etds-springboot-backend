//
//package domain.in.rjsa.security;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
//import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigAD extends WebSecurityConfigurerAdapter {
//
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
//				.and().logout() .logoutUrl("/logout")
//	            .logoutSuccessUrl("/logout")
//	            // Ensure the correct logout success handler is set
//	            .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler()).permitAll().and().authorizeRequests()
//				.antMatchers("/login", "/logout", "/static/css/fonts/untitled-font-2*", "/static/img/favicon.ico",
//						"/static/img/tds.png", "/static/assets/js/bootstrap.bundle.min.js", "/static/img/ioblogo.png",
//						"/static/js/bootstrap.min.js", "/static/js/lib/bootstrap.js", "/static/js/jquery.min.js",
//						"/static/css/font-awesome.min.css","/static/fonts/css/font-awesome.css",
//						"/static/css/signin.css", "/static/css/bootstrap.min.css", "/static/img/TOS.png",
//						"/apidownloadCertificate/**")
//				.permitAll().anyRequest().authenticated().and().exceptionHandling()
//				.accessDeniedPage("/Error.html") //For showing Custom Authentication Page
//				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //To generate CSRF Token
//				.and().headers()
//		            .httpStrictTransportSecurity() //To set Strict Transport Security
//		                .maxAgeInSeconds(31536000)
//		                .includeSubDomains(true) 
//		                .preload(true)
//		                .and()
//		                .frameOptions().sameOrigin()
//		                .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy",
//		                		 "upgrade-insecure-requests; " +
//		                				    "block-all-mixed-content; " +
//		                				    "https: data:; " +  
//		                				    "unsafe-inline; " + 
//		                				    "unsafe-eval"       
//		                    ))
//		                .addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options", "nosniff")) //To add X-Content-Type-Options
//		                .addHeaderWriter(new ReferrerPolicyHeaderWriter(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)); 
//		        http.sessionManagement() //To set maximum 1 session for a user
//		        	.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
//		            .maximumSessions(1)
//		            .maxSessionsPreventsLogin(false) //This will block multiple login for a user
//		            .sessionRegistry(sessionRegistry());
//	}
//
//	@Bean
//    public SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//	
//	@Bean
//	public ConcurrentSessionControlAuthenticationStrategy sessionAuthenticationStrategy() {
//	    return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
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
//
//}