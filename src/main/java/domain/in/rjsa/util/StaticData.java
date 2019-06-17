package domain.in.rjsa.util;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
	
	public static List<String> documentStatus= new ArrayList<String>() {{
		add("Pending Verification");
		add("Verified");
		add("Rejected");
		
	}};
	public static List<String> employeeStatus= new ArrayList<String>() {{
		add("Verified");
		add("Pending Verification");
		
	}};
	public static List<String> relation= new ArrayList<String>() {{
		add("");
		add("Verified");
		add("Rejected");
		
	}}; 
	
	public static String fy ="2018-19";

}
