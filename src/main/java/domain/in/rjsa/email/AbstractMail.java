package domain.in.rjsa.email;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.excel.ReadImage;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.util.StaticData;
import freemarker.template.TemplateException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public abstract class AbstractMail {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	StaticDataDao sDao;

	Date d = new Date();
	SimpleDateFormat Dateformat = new SimpleDateFormat("dd-MM-yyyy");
	String date = Dateformat.format(d);

//	String fromEmail = "noreply@taxosmart.in";
//	String company = "Taxosmart";
//	final String HOST = "smtp.zeptomail.in";
//	final String USERNAME = "emailapikey";
//	final String SMTP_PASSWORD = "PHtE6r1YQuruim4v8xACtqe7RJH2Nowsq7hlKAMTtIwUD/5VGU1SqNwixzXirh55BqFGR//Kz9g9s7ub5+uEJGvqZGxNCGqyqK3sx/VYSPOZsbq6x00esF0cdETeUo7tctJt3CLRu9nSNA==";

	String fromEmail = "tds.support@newindia.co.in";
	String company = "New India Assurance";
	final String HOST = "smtp.gmail.com";
	final String USERNAME = "tds.support@newindia.co.in";
	final String SMTP_PASSWORD = "zlkt qykg effi arxa";

	Session session;

	public void sendEmail(HashMap<String, String> emailsTo) {
		setStatic();
		String to = emailsTo.get("emailTo").toString();
		String messageToReciever = createMessage(emailsTo);
		emailsTo.put("to", to);
		emailsTo.put("message", messageToReciever);
		emailsTo.put("cc", StaticData.CcMail);

		try {
			logger.info("All Details updated");
			sendSimpleMessage(emailsTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendSimpleMessage(HashMap<String, String> mail)
			throws MessagingException, IOException, TemplateException {
		setSession();
		if (mail != null) {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, company));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.get("to").toString()));
			if (mail.containsKey("cc") && mail.get("cc") != null) {
				message.setRecipient(Message.RecipientType.CC, new InternetAddress(mail.get("cc").toString()));
			}
			message.setHeader("X-Priority", "1");
			message.setSubject(mail.get("subject").toString());
			MimeMultipart multiPart = new MimeMultipart("related");
			String cid1 = String.format("%s-%s", "logo1", UUID.randomUUID());
			String cid2 = String.format("%s-%s", "logo2", UUID.randomUUID());
			String cid3 = String.format("%s-%s", "logo3", UUID.randomUUID());
			String cid4 = String.format("%s-%s", "logo4", UUID.randomUUID());
			mail.put("cid1", cid1);
			mail.put("cid2", cid2);
			mail.put("cid3", cid3);
			mail.put("cid4", cid4);
			MimeBodyPart htmlText = new MimeBodyPart();
			String messageBodyPart = "";
			messageBodyPart += messageBody(mail);
			htmlText.setContent(messageBodyPart, "text/html");
			multiPart.addBodyPart(htmlText);

			MimeBodyPart imagePart1 = new MimeBodyPart();
			imagePart1.setContentID("<" + cid1 + ">");
			imagePart1.setDisposition(MimeBodyPart.INLINE);

			try {
				imagePart1.attachFile(new ReadImage().initializeResourceString("images/Logos/taxosmart_logo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			multiPart.addBodyPart(imagePart1);
			message.setContent(multiPart);

			MimeBodyPart imagePart3 = new MimeBodyPart();
			imagePart3.setContentID("<" + cid3 + ">");
			imagePart3.setDisposition(MimeBodyPart.INLINE);

			try {
				imagePart3.attachFile(new ReadImage().initializeResourceString("images/Logos/website.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			multiPart.addBodyPart(imagePart3);
			message.setContent(multiPart);

			Transport.send(message);
			logger.info("Mail sent");
		}
	}

	private void setStatic() {
		HashMap<String, Object> sd = new HashMap<String, Object>();
		List<StaticDataModel> list = sDao.findall(sd, 0, 100);
		String xString;
		for (StaticDataModel list1 : list) {
			String key = list1.getKey();
			switch (key) {
			case "ccMail":
				xString = list1.getValue();
				StaticData.CcMail = xString;
				break;
			default:
				System.out.println("Not Match");
				break;
			}
		}
	}

	public abstract String createMessage(HashMap<String, String> emailsTo);

	public abstract String messageBody(HashMap<String, String> mail);

	public void setSession() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.from", fromEmail);
		props.put("mail.smtp.timeout", "10000");
		session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, SMTP_PASSWORD);
			}
		});
	}
}
