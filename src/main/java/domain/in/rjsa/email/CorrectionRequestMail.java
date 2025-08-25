package domain.in.rjsa.email;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import domain.in.rjsa.model.form.CorrectionRequest;

@Service
public class CorrectionRequestMail extends AbstractMail {

	public void sendEmail(HashMap<String, String> emailsTo, CorrectionRequest cr) {
		HashMap<String, String> mail = new HashMap<String, String>();
		String to = emailsTo.get("emailTo").toString();
		emailsTo.put("branchCode", cr.getBranchCode().toString());
		emailsTo.put("date", cr.getCorrectionRequestDate().toString());
		emailsTo.put("ticketNumber", cr.getTicketNumber().toString());

		String s = emailsTo.get("subject") + " - TDS/TCS ";
		if (cr.getTypeOfForm() != null) {
			s = s + cr.getTypeOfForm().split(Pattern.quote("-"), -1)[0];
			mail.put("form", cr.getTypeOfForm());
			emailsTo.put("form", cr.getTypeOfForm());
		}
		if (cr.getFy() != null) {
			s = s + " Correction Request for FY " + cr.getFy();
			mail.put("fy", cr.getFy());
			emailsTo.put("fy", cr.getFy());
		}
		if (cr.getQuarter() != null) {
			s = s + " , in " + cr.getQuarter();
			mail.put("quarter", cr.getQuarter());
			emailsTo.put("quarter", cr.getQuarter());
		}
		if (cr.getStatus() != null) {
			if (cr.getStatus().equals("Pending Checker Approval")) {
				emailsTo.put("status", "Pending Tax Team Approval");
				mail.put("status", "Pending Tax Team Approval");
			} else {
				emailsTo.put("status", cr.getStatus());
				mail.put("status", cr.getStatus());
			}
		}
		mail.put("subject", s);
		String messageToReciever = createMessage(emailsTo);
		mail.put("to", to);
		mail.put("message", messageToReciever);
		try {
//			File convFile = new File(blob.getOriginalFilename());
//			blob.transferTo(convFile);
//			sendSimpleMessage(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String messageBody(HashMap<String, String> mail) {
		String messageBody = "<html> 	\r\n" + "<body>\r\n" + "<p>Dear Sir/Ma'am,</p><p>" + mail.get("message")
				+ "</p>" + getHeader(mail)
				+ "<li>Step 1 : Visit url <a href=\"tds.newindia.co.in\">tds.newindia.co.in</a></li>"
				+ "<li>Step 2 : Login using your credentials</li>" + "<li>Step 3 : Click on \"Correction Request\"</li>"
				+ "<li>Step 4 : Search on the basis of the ticket number</li>"
				+ "<li>Step 5 : Double click on the row with the ticket number.</li>"
				+ "<li>Step 6 : If You want to Add Remark then click on \"Add Remark\" Button and add remark with file if any.</li>"
				+ "<p>Regards,</p>"
				+ "																	<a clicktracking=\"off\"\r\n"
				+ "																			href=\"https://www.taxosmart.com/\"><img width = 200 \r\n"
				+ "																			src=\"cid:"
				+ mail.get("cid1")
				+ "\"																	   ></a>\r\n"
				+ "<table style=\"width:25%;\">\r\n" + "<tr>\r\n"
				+ "																	<td><img width = 20 height = 20 src=\"cid:"
				+ mail.get("cid3")
				+ "\"></td><td><a clicktracking=\"off\"href=\"https://www.taxosmart.com/\"><span style=\"margin-top:-1%\">www.taxosmart.com</span></a></td>\r\n"
				+ "																</tr>\r\n"
				+ "															</table>\r\n" + "\r\n" + "</body>\r\n"
				+ "</html>";

		return messageBody;
	}

	private String getHeader(HashMap<String, String> mail) {
		String header = "<p>Kindly follow the below mentioned steps to check the status of the correction request.</p>";
		if (!mail.get("status").trim().equalsIgnoreCase("Pending Tax Team Approval")) {
			header = "<p>For any further details kindly follow the below mentioned steps.</p>";
		}
		return header;
	}

	@Override
	public String createMessage(HashMap<String, String> emailsTo) {
		String message = emailsTo.get("message");
		if (emailsTo.containsKey("ticketNumber")) {
			message = message + "<li><b>Ticket Number : " + emailsTo.get("ticketNumber") + "</b></li>";
		}
		if (emailsTo.containsKey("form")) {
			message = message + "<li><b>Form Type     : " + emailsTo.get("form") + "</b></li>";
		}
		if (emailsTo.containsKey("fy")) {
			message = message + "<li><b>F.Y.          : " + emailsTo.get("fy") + "</b></li>";
		}
		if (emailsTo.containsKey("quarter")) {
			message = message + "<li><b>Quarter       : " + emailsTo.get("quarter") + "</b></li>";
		}
		if (emailsTo.containsKey("status")) {
			message = message + "<li><b>Status        : " + emailsTo.get("status") + "</b></li>";
		}
		return message;
	}
}