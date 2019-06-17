package domain.in.rjsa.mail;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
@Service
public class InvoiceMailSender extends AbstractMailSender {

	static final Logger logger = LoggerFactory.getLogger(InvoiceMailSender.class);
	@Override
	protected MimeMessagePreparator getMessagePreparator(
			Map<String, Object> model) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject( "Your order invoice");
                helper.setFrom("order-update@taxosmart.com");
               helper.setTo((model.get("customerEmail")).toString());
                String text = geFreeMarkerTemplateContent(model);
                helper.setText(text, true);
 
            }
        };
        return preparator;
	}

	@Override
	protected String geFreeMarkerTemplateContent(Map<String, Object> model) {
		  StringBuffer content = new StringBuffer();
	        try{
	         content.append(FreeMarkerTemplateUtils.processTemplateIntoString( 
	                 freemarkerConfiguration.getTemplate("invoice.txt"),model));
	         return content.toString();
	        }catch(Exception e){
	            logger.error("Exception occured while processing invoiceTemplate:"+e);
	        }
	          return "";
	}

}
