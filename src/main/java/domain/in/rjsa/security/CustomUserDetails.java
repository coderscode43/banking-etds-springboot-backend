package domain.in.rjsa.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

public class CustomUserDetails implements LdapUserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String physicalDeliveryOfficeName;
	private LdapUserDetails details;
    public CustomUserDetails(LdapUserDetails details) {
    	this.details=details;
		// TODO Auto-generated constructor stub
	}
	public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
    }
    public String getPhysicalDeliveryOfficeName() {
        return this.physicalDeliveryOfficeName;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return details.getAuthorities();
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return details.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return details.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return details.isAccountNonExpired();
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return details.isAccountNonLocked();
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return details.isCredentialsNonExpired();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return details.isEnabled();
	}
	@Override
	public void eraseCredentials() {
		// TODO Auto-generated method stub
		details.eraseCredentials();
		
	}
	@Override
	public String getDn() {
		// TODO Auto-generated method stub
		return details.getDn();
	}
	
}
