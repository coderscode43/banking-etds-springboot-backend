package domain.in.rjsa.confirguration;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@ComponentScan({ "domain.in.rjsa.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class MailSenderConfig {

	@Autowired
    private Environment environment;
	final Logger logger = LoggerFactory.getLogger(MailSenderConfig.class);


	//Java email Sende credentials of Amazon SES
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Using Amazon SES.
        mailSender.setHost(environment.getRequiredProperty("smtp.host"));
        mailSender.setPort(587);
        mailSender.setUsername(environment.getRequiredProperty("smtp.userName"));
        mailSender.setPassword(environment.getRequiredProperty("smtp.password"));
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }


    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/WEB-INF/fmtemplates/emailTemplate/");
        return bean;
    }
}