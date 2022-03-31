package domain.in.rjsa.security;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("customUserDetailsService")
//	UserDetailsService userDetailsService;
//	@Autowired
//	HibernateTokenRepositoryImpl tokenRepository;
//
//
//	@Override
//	public void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth.userDetailsService(userDetailsService);
//		auth.authenticationProvider(authenticationProvider());
//	}
//	
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	
//		
//		http.formLogin().loginPage("/login.jsp").loginProcessingUrl("/login").defaultSuccessUrl("/", true).permitAll().and().authorizeRequests()
//        .antMatchers( "/login","/","/static/css/fonts/untitled-font-2*","/static/img/favicon.ico","/static/img/tds.png").permitAll().anyRequest().authenticated()
//        .and().rememberMe().rememberMeParameter("remember-me")
//				.tokenRepository(tokenRepository).tokenValiditySeconds(10000)
//				.and().exceptionHandling()
//				.accessDeniedPage("/Access_Denied").and().csrf().disable().headers().frameOptions().sameOrigin();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
//
//	
//
//	@Bean
//	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
//		return new AuthenticationTrustResolverImpl();
//	}
//
//	@Bean
//	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
//		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
//				"remember-me", userDetailsService, tokenRepository);
//		return tokenBasedservice;
//	}
//
//}