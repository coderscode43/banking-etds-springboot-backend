package domain.in.rjsa.model.fy;
import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class MonthlyChallan extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	public Long id;		
	public Long	 branchCode;
	public String month;
	public Double amtAsPerFinacle;
	public Double amtAsPerTaxCalculation;
	public String challanHeading;
	public String fy;
	public Double amountDiff;
	public String remarks;
	
	
    public void setData(JSONObject dataObject) throws ParseException{
        this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
        this.month = dataObject.get("MONTH").toString();
        this.amtAsPerFinacle = Double.valueOf(dataObject.get("AMTASPERFINACLE").toString());
        this.amtAsPerTaxCalculation = Double.valueOf(dataObject.get("AMTASPERTAXCALCULATION").toString());
        this.challanHeading = dataObject.get("CHALLANHEADING").toString();
        this.fy = dataObject.get("FY").toString();
        this.amountDiff = Double.valueOf(dataObject.get("AMOUNTDIFF").toString());
        this.remarks = dataObject.get("REMARKS").toString();
    }
}
