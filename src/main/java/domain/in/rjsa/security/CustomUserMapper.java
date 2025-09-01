package domain.in.rjsa.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.web.ApplicationCache;

public class CustomUserMapper extends LdapUserDetailsMapper{
	
	static final Logger logger = LoggerFactory.getLogger(CustomUserMapper.class);

	@Autowired
	ApplicationCache applicationCache;
	
//    @Override
//    public CustomUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities){
//    	LdapUserDetails details = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);
//    	
//        CustomUserDetails customUserDetails = new CustomUserDetails(details);
//        
//       customUserDetails.setPhysicalDeliveryOfficeName(ctx.getStringAttribute("physicalDeliveryOfficeName"));
////        customUserDetails.setPhysicalDeliveryOfficeName(ctx.getStringAttribute("cn"));
//        return customUserDetails;
//    }
	
	@Override
	public CustomUserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		LdapUserDetails details = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);

		CustomUserDetails customUserDetails = new CustomUserDetails(details);

//	    // Retrieve all attributes
//	    Attributes attributes = ctx.getAttributes();
//	    
//	    // Print all attributes in a formatted way
//	    logger.info("============================ LDAP Attributes: ============================");
//	    logger.info("----------------------------");
//	    
//	    try {
//	        // Get all attribute IDs
//	        Enumeration<String> attributeIDs = attributes.getIDs();
//	        while (attributeIDs.hasMoreElements()) {
//	            String attributeID = attributeIDs.nextElement();
//	            Attribute attribute = attributes.get(attributeID);
//	            
//	            if (attribute != null) {
//	            	logger.info("Attribute:" + attributeID);
//	                
//	                // Print all values of the attribute
//	                for (int i = 0; i < attribute.size(); i++) {
//	                	logger.info("Value : " + attribute.get(i));
//	                	logger.info("----------------------------");
//	                }
//	            }
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
		logger.info("============================ BranchCode from LDAP Attribute: ============================");
		logger.info("branchCode - " + ctx.getStringAttribute("postofficebox"));
		logger.info("============================ BranchCode from LDAP Attribute: ============================");

		// For War
//		customUserDetails.setBranchCode(ctx.getStringAttribute("postofficebox"));

		// For Local
		customUserDetails.setBranchCode("1015");
		domain.in.rjsa.model.form.UserDetails user = null;

		try {
			user = applicationCache.getAdminUser(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String branchCode = customUserDetails.getBranchCode();

		if (branchCode != null) {
		    Branch branch = applicationCache.getBranch(Long.valueOf(branchCode));
		    if (user == null && branch == null) {
		        throw new BadCredentialsException("Kindly contact the CO and add the branch - " + branchCode);
		    }
		} else if (user == null) {
		    throw new BadCredentialsException("Authentication failed: Branchcode could not be found");
		}


		// Set the attribute in CustomUserDetails
		customUserDetails.setPhysicalDeliveryOfficeName(ctx.getStringAttribute("physicalDeliveryOfficeName"));

		return customUserDetails;
	}

}
