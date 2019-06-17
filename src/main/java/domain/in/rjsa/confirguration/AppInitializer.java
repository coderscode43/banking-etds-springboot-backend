package domain.in.rjsa.confirguration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import domain.in.rjsa.security.SecurityConfiguration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class,HibernateConfiguration.class, SecurityConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
}