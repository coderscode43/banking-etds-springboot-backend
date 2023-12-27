package domain.in.rjsa.security;

import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

public class CustomUserMapper extends LdapUserDetailsMapper{

    @Override
    public CustomUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities){
    	LdapUserDetails details = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);
    	
        CustomUserDetails customUserDetails = new CustomUserDetails(details);
        
       customUserDetails.setPhysicalDeliveryOfficeName(ctx.getStringAttribute("physicalDeliveryOfficeName"));
//        customUserDetails.setPhysicalDeliveryOfficeName(ctx.getStringAttribute("cn"));
        return customUserDetails;
    }

}
