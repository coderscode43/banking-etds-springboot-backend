package domain.in.rjsa.model.fy;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class TotalAmount extends CommonModelAbstract<TotalAmount>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5129286740595140551L;
	public Long id;
	public String custVendId;
	public String pan;
	public String sectionCode;
	public String challanHeading;
	public String month;
	public String fy;
	public Double totalAmountPaidRaw;
	public Double totalAmountPaidUpload;
	public Double totaltaxRaw;
	public Double totalTaxUploaded;
	public String remark;
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
