package domain.in.rjsa.web;

import java.util.HashMap;
import java.util.Map;

public class StaticData {
	
	public  static final Map<String, String> entityMap;
	
	
	public  static final Map<Long, String> fyMonth;
    static
    {
    	fyMonth = new HashMap<Long, String>();
    	fyMonth.put(1L, "April");
    	fyMonth.put(2L, "May");
    	fyMonth.put(3L, "June");
    	fyMonth.put(4L, "July");
    	fyMonth.put(5L, "August");
    	fyMonth.put(6L, "September");
    	fyMonth.put(7L, "October");
    	fyMonth.put(8L, "November");
    	fyMonth.put(9L, "December");
    	fyMonth.put(10L, "January");
    	fyMonth.put(11L, "February");
    	fyMonth.put(12L, "March");
    }
    
    
    public static final String partyTypeCustomer="customer";
    public static final String partyTypeVendor="vendor";
    
    static
    {
    	entityMap=new HashMap<String, String>();
    	entityMap.put("employee","Employee");
    	entityMap.put("groups","Groups");
    	entityMap.put("pdocument","Personal Documents");
    	entityMap.put("ped","Previous Employee Detail");
    	entityMap.put("hpi","House Property Income");
    	entityMap.put("oid","Other Income Details");
    	entityMap.put("profile","Profile");
    	entityMap.put("sec10(13a)","House Rent Allowance");
    	entityMap.put("sec10(5)","Leave Travel Concession");
    	entityMap.put("sec80c","Section 80C/80CCC/80CCD");
    	entityMap.put("sec80ccd(1b)","Section 80CCD (1b)");
    	entityMap.put("sec80d","Section 80D");
    	entityMap.put("sec80dd","Section 80DD");
    	entityMap.put("sec80ddb","Section 80DDB");
    	entityMap.put("sec80ee","Section 80EE");
    	entityMap.put("sec80g","Section 80G");
    	entityMap.put("sec80gg","Section 80GG");
    	entityMap.put("sec80gga","Section 80GGA");
    	entityMap.put("sec80ggc","Section 80GGC");
    	entityMap.put("sec80tta","Section 80TTA");
    	entityMap.put("sec80u","Section 80U");
    	entityMap.put("login","Login");
    	
    	    }


}
