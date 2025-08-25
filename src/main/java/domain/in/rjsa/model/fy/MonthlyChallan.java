package domain.in.rjsa.model.fy;
import java.text.ParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.monthlyChallan1")
public class MonthlyChallan extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;		
	//@Column(name = "branchCode")
	public Long	 branchCode;
	//@Column(name = "month")
	public String month;
	//@Column(name = "amtAsPerFinacle")
	public Double amtAsPerFinacle;
	//@Column(name = "amtAsPerTaxCalculation")
	public Double amtAsPerTaxCalculation;
	//@Column(name = "challanHeading")
	public String challanHeading;
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "amountDiff")
	public Double amountDiff;
	//@Column(name = "remarks")
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
