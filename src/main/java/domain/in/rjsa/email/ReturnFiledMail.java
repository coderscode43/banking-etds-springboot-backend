package domain.in.rjsa.email;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ReturnFiledMail extends AbstractMail {

	public String createMessage(HashMap<String, String> map) {
		String message = "We are pleased to inform you that the certificate for the timely filed TDS regular return has been generated. ";
		return message;
	}

	public String messageBody(HashMap<String, String> mail) {
		String messageBody = "<html> 	\r\n" + "<body>\r\n" + "<p>Dear Sir/Ma'am,</p>\r\n" + mail.get("message")
				+ "<p>Kindly follow the below mentioned steps to download the certificate</p>"
				+ "<li>Step 1 : Visit url <a href=\"tds.newindia.co.in\">tds.newindia.co.in</a></li>"
				+ "<li>Step 2 : Login using your credentials</li>"
				+ "<li>Step 3 : Click on \"Download 16A/16/27D\" and then click on \"Download All Certificates\"</li>" + "<li>Step 4 : Select  <b> TAN: "
				+ mail.get("tan").split(Pattern.quote("-"), -1)[0] + ", FY: " + mail.get("fy") + ", Quarter: " + mail.get("quarter") + "</b> and <b>Form: "
				+ mail.get("form") + "</b></li>" + "<li>Step 5 : Click on \"Downlaod\" and download certificate</li>"
				+ "<p>Regards,</p>"
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
