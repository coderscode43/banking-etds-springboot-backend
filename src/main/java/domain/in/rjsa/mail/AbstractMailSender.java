package domain.in.rjsa.mail;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import freemarker.template.Configuration;

public abstract class AbstractMailSender {
	static final Logger logger = LoggerFactory.getLogger(AbstractMailSender.class);
    @Autowired
    JavaMailSender mailSender;
     
    @Autowired
    Configuration freemarkerConfiguration;
 

    public void sendEmail(Map<String, Object> model) {
        MimeMessagePreparator preparator = getMessagePreparator(model);
         
        try {
            mailSender.send(preparator);
            logger.info("Email has been sent");
        }
        catch (MailException ex) {
            logger.error("Error in sending email :", ex);
        }
    }
 
    protected abstract MimeMessagePreparator getMessagePreparator(Map<String, Object> model);
 
 
    protected abstract String geFreeMarkerTemplateContent(Map<String, Object> model);
}
