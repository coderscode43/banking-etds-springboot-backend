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
//@Table(name = "AAACN4165C_2324.TotalAmount")
public class TotalAmount extends CommonModelAbstract<TotalAmount>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5129286740595140551L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Column(name = "custVendId")
	public String custVendId;
	//@Column(name = "pan")
	public String pan;
	//@Column(name = "sectionCode")
	public String sectionCode;
	//@Column(name = "challanHeading")
	public String challanHeading;
	//@Column(name = "month")
	public String month;
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "totalAmountPaidRaw")
	public Double totalAmountPaidRaw;
	//@Column(name = "totalAmountPaidUpload")
	public Double totalAmountPaidUpload;
	//@Column(name = "totalTaxRaw")
	public Double totaltaxRaw;
	//@Column(name = "totalTaxUploaded")
	public Double totalTaxUploaded;
	//@Column(name = "remark")
	public String remark;
	//@Column(name = "source")
	public String source;
	
	
	public void setData(JSONObject dataObject) throws ParseException{
	    this.custVendId = dataObject.get("CUSTVENDID").toString();
	    this.pan = dataObject.get("PAN").toString();
	    this.sectionCode = dataObject.get("SECTIONCODE").toString();
	    this.challanHeading = dataObject.get("CHALLANHEADING").toString();
	    this.month = dataObject.get("MONTH").toString();
	    this.fy = dataObject.get("FY").toString();
	    
	    this.totalAmountPaidRaw = Double.valueOf(dataObject.get("TOTALAMOUNTPAIDRAW").toString());
	    this.totalAmountPaidUpload = Double.valueOf(dataObject.get("TOTALAMOUNTPAIDUPLOAD").toString());
	    this.totaltaxRaw = Double.valueOf(dataObject.get("TOTALTAXRAW").toString());
	    this.totalTaxUploaded = Double.valueOf(dataObject.get("TOTALTAXUPLOADED").toString());
	    this.remark = dataObject.get("REMARK").toString();
	    this.source = dataObject.get("SOURCE").toString();
	}

}
