package domain.in.rjsa.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.LoginService;
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private LoginService loginService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Login user = loginService.getLogin(userName);
		logger.info("User : {}", user);
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				user.getEnabled(), true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Login user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (int i = 0; i <= user.getAccessLevel(); i++) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + String.valueOf(i)));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
