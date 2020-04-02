package domain.in.rjsa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaticData {
	
	public static List<String> documentStatus= new ArrayList<String>() {{
		add("Pending for approval");
		add("Verified");
		add("Rejected");
		
	}};
	public static List<String> entity = new ArrayList<String>() {{
		add("form26QDeductee");//0
		add("form27QDeductee");//1
		add("form24QDeductee");//2
		add("form26QUpdateRequestDetail");//3
		add("form27QUpdateRequestDetail");//4
		add("form24QUpdateRequestDetail");//5
		add("form24QSalary");//6
		add("form24QSalaryUpdateRequestDetail");//7
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
	
	public static String presentQ="F9FDBF";
	public static String presentFiling="FDBEBE";
	public static String presentChallan="BECFFD";
	
	static HashMap<String,Double> sectionThreshold = new HashMap<String,Double>(){{
		put("192^201920^1", null);
		put("192^201920^2", null);
		put("192^201920^3", 30d);
		   
		put("192A^201920^1", 10d);
		put("192A^201920^2", null);
		put("192A^201920^3", 20d);
		   
		put("193^201920^1", 10d);
		put("193^201920^2", 10d);
		put("193^201920^3", 20d);
		   
		put("194^201920^1", 10d);
		put("194^201920^2", 10d);
		put("194^201920^3", 20d);
		   
		put("194A^201920^1", 10d);
		put("194A^201920^2", 10d);
		put("194A^201920^3", 20d);
		   
		put("194A^201920^1", 10d);
		put("194A^201920^2", 10d);
		put("194A^201920^3", 20d);
		   
		put("194B^201920^1", 30d);
		put("194B^201920^2", 30d);
		put("194B^201920^3", 30d);
		   
		put("194B^201920^1", 30d);
		put("194B^201920^2", 30d);
		put("194B^201920^3", 30d);
		   
		put("194C^201920^1", 1d);
		put("194C^201920^2", 2d);
		put("194C^201920^3", 20d);
		   
		put("194C^201920^1", 0d);
		put("194C^201920^2", 0d);
		put("194C^201920^3", 20d);
		   
		put("194C^201920^1", 1d);
		put("194C^201920^2", 2d);
		put("194C^201920^3", 20d);
		   
		put("194D^201920^1", 5d);
		put("194D^201920^2", 10d);
		put("194D^201920^3", 20d);
		   
		put("194DA^201920^1", 1d);
		put("194DA^201920^2", 1d);
		put("194DA^201920^3", 20d);
		   
		put("194E^201920^1", 20d);
		put("194E^201920^2", 20d);
		put("194E^201920^3", 20d);
		   
		put("194EE^201920^1", 10d);
		put("194EE^201920^2", 10d);
		put("194EE^201920^3", 20d);
		   
		put("194F^201920^1", 20d);
		put("194F^201920^2", 20d);
		put("194F^201920^3", 20d);
		   
		put("194G^201920^1", 5d);
		put("194G^201920^2", 5d);
		put("194G^201920^3", 20d);
		   
		put("194H^201920^1", 5d);
		put("194H^201920^2", 5d);
		put("194H^201920^3", 20d);
		   
		put("194I^201920^1", 10d);
		put("194I^201920^2", 10d);
		put("194I^201920^3", 20d);
		   
		put("194I^201920^1", 2d);
		put("194I^201920^2", 2d);
		put("194I^201920^3", 20d);
		   
		put("194IA^201920^1", 1d);
		put("194IA^201920^2", 1d);
		put("194IA^201920^3", 20d);
		   
		put("194IB^201920^1", 5d);
		put("194IB^201920^2", 5d);
		put("194IB^201920^3", 20d);
		   
		put("194J^201920^1", 10d);
		put("194J^201920^2", 10d);
		put("194J^201920^3", 20d);
		   
		put("194J^201920^1", 2d);
		put("194J^201920^2", 2d);
		put("194J^201920^3", 20d);
		   
		put("194LA^201920^1", 10d);
		put("194LA^201920^2", 10d);
		put("194LA^201920^3", 20d);
	   }
	};
	   
	   
	static HashMap<String,Double> sectionRate = new HashMap<String,Double>(){{
		put("192^201920", null);
		put("192A^201920", 50000d);
		put("193^201920", 10000d);
		put("194^201920", 2500d);
		put("194A^201920", 40000d);
		put("194A^201920", 5000d);
		put("194B^201920", 10000d);
		put("194B^201920", 10000d);
		put("194C^201920", 30000d);
		put("194C^201920", null);
		put("194C^201920", 30000d);
		put("194D^201920", 15000d);
		put("194DA^201920", 100000d);
		put("194E^201920", null);
		put("194EE^201920", 2500d);
		put("194F^201920", null);
		put("194G^201920", 15000d);
		put("194H^201920", 15000d);
		put("194I^201920", 240000d);
		put("194I^201920", 240000d);
		put("194IA^201920", 5000000d);
		put("194IB^201920", 50000d);
		put("194J^201920", 30000d);
		put("194J^201920", 30000d);
		put("194LA^201920", 250000d);   
		
	   }

	   };
	   
	
	
	

}
