package domain.in.rjsa.email;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ReminderMail extends AbstractMail {

	public String createMessage(HashMap<String, String> map) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		String message = "We would like to remind you that the deadline for filing ";
		String dueDate = null;
		if (map != null) {
			if (map.containsKey("quarter")) {
				if (!map.get("quarter").equalsIgnoreCase("null")) {
					message = message + "the <b>" + map.get("quarter") + "</b>";
					switch (map.get("quarter")) {
					case "Q1":
						dueDate = "July 31st," + year;
						break;
					case "Q2":
						dueDate = "October 31st," + year;
						break;
					case "Q3":
						dueDate = "January 31st," + year;
						break;
					case "Q4":
						dueDate = "May 31st," + year;
						break;
					default:
						break;
					}
				}
			}
			if (map.containsKey("form")) {
				if (!map.get("form").equalsIgnoreCase("null")) {
					message = message + ", <b> FORM : " + map.get("form") + "</b> return";
				}
			}
			if (map.containsKey("fy")) {
				if (!map.get("fy").equalsIgnoreCase("null")) {
					message = message + " for the <b>F.Y. : " + map.get("fy") + "</b>";
				}
			}
			if (map.containsKey("tan")) {
				if (!map.get("tan").equalsIgnoreCase("null")) {
					message = message + " of <b> TAN : " + map.get("tan").split(Pattern.quote("-"), -1)[0] + "</b>";
				}
			}
			if (dueDate != null) {
				message = message + ", is <b>" + dueDate +"</b>";
			}
			message = message
					+ ". Please ensure that you provide us with the necessary data files for the TDS return filing by as soon as possible.";
//		
		}
		return message;

	}

	public String messageBody(HashMap<String, String> mail) {
		String messageBody = "<html> 	\r\n" + "<body>\r\n" + "<p>Dear Sir/Ma'am,</p>\r\n" + mail.get("message")
				+ "<p>Please follow the steps below to submit the data:</p>"
				+ "<li>Step 1 : Visit url <a href=\"tds.newindia.co.in\">tds.newindia.co.in</a></li>"
				+ "<li>Step 2 : Login using your credentials</li>" + "<li>Step 3 : Click on \"Regular Return\"</li>"
				+ "<li>Step 4 : Double click on the entry where <b>FY: " + mail.get("fy") + ", Quarter: "
				+ mail.get("quarter") + "</b> and <b>Form: " + mail.get("form") + "</b></li>"
				+ "<li>Step 5 : Click on \"Add Remark\" Button and add Data with remark</li>" + "<p>Regards,</p>"
				+ "																	<a clicktracking=\"off\"\r\n"
				+ "																			href=\"https://www.taxosmart.com/\"><img width = 200 \r\n"
				+ "																			src=\"cid:"
				+ mail.get("cid1")
				+ "\"																	   ></a>\r\n"
				+ "<table style=\"width:25%;\">\r\n" + "<tr>\r\n"
				+ "																	<td><img width = 20 height = 20 src=\"cid:"
				+ mail.get("cid3") + "\"																	>\r\n"
				+ "																	</td><td><a clicktracking=\"off\"href=\"https://www.taxosmart.com/\"><span style=\"margin-top:-1%\">www.taxosmart.com</span></a></td>\r\n"
				+ "																</tr>\r\n"
				+ "															</table>\r\n" + "\r\n" + "</body>\r\n"
				+ "</html>";

		return messageBody;
	}

}
