package domain.in.rjsa.util;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
	
	public static List<String> documentStatus= new ArrayList<String>() {{
		add("Pending for approval");
		add("Verified");
		add("Rejected");
		
	}};
	
	public static String[] financialYear =null;
	public static String[] typeOfDeductee =null;
	public static String[] typeOfCertificate =null;
	public static String[] Quarter =null;
	public static String path =null;
	public static String[] State =null;
	public static String[] Month =null;
	public static String ClientName =null;
	public static String ClientPAN =null;
	public static String[] Tan =null;
	public static String[] Section =null;
	public static String CertificatePath =null;
	/* public static String[] ChallanMismatch =null; */
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXSAYALI
	public static List<String> typeOfAdmin= new ArrayList<String>() {{
		add("admin");
		add("SuperAdmin");
	}};
	

}
