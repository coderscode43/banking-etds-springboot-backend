package domain.in.rjsa.util;

import java.util.ArrayList;
import java.util.List;

public class StaticData {

	public static List<String> documentStatus = new ArrayList<String>() {
		{
			add("Pending for approval");
			add("Verified");
			add("Rejected");

		}
	};

	public static String[] financialYear = null;
	public static String[] typeOfDeductee = null;
	public static String[] typeOfCertificate = null;
	public static String[] Quarter = null;
	public static String path = null;
	public static String[] State = null;
	public static String[] Month = null;
	public static String ClientName = null;
	public static String ClientPAN = null;
	public static String[] Tan = null;
	public static String[] Section = null;
	public static String[] Form = null;
	public static String CertificatePath = null;
	public static String TaxAuditReportPath = null;
	public static String directDownloadAuth = "";
	public static String command = null;
	public static String[] status = null;
	public static String[] typeOfCorrection = null;
	public static String[] typeOfForm = null;
	public static String documentSave = null;
	public static String[] regularReturnStatus = null;
	public static String[] exemption = null;
	public static String[] typeOfReport = null;
	public static String latestFy = null;

	/* public static String[] ChallanMismatch =null; */
	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXSAYALI
	public static List<String> typeOfAdmin = new ArrayList<String>() {
		{
			add("admin");
			add("SuperAdmin");
		}
	};

	public static List<String> certificateType = new ArrayList<String>() {
		{
			add("Form16A");
			add("Form16");
			add("Form27D");

		}
	};

	public static List<String> formType = new ArrayList<String>() {
		{
			add("24Q");
			add("26Q");
			add("27EQ");
			add("27Q");
		}
	};

	public static String CcMail;
	
	public static String annualReportPath = null;

}
