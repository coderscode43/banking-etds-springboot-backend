package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class G15 extends CommonModelAbstract{
	
	
private static final long serialVersionUID = 1L;
	
	public Long id;		
	
	public String uniqueIdentificationNo;
	
	private String identificationNumberofRelevantInvestmentAccount;
	
	private String natureofIncome;
	
	public String sectionUnderWhichTaxisDeductible;	
	
	public Double amountofIncome;
	
	private String nameofAssesseeDeclarant ;

	private String panoftheAssessee;
	
	private String aadhaarNumber;
	
	private String status;
	
	private String subStatus;
	
	private String previousYearforwhichdeclarationisbeingmade;
	
	private String residentialStatus;
	
	private String country;
	
	private String flatDoorBuilding;
	
	private String roadStreetBlockSector;
	
	private Long pincode;
	
	private String postOffice;
	
	private String areaLocality;
	
	private String district;
	
	private String state;
	
	private String email;
	
	private String telephoneNo;
	
	private String mobileNo;
	
	private String whetherAssessedtotaxundertheIncometaxAct1961;
	
	private String ifyeslatestassessmentyearforwhichassessed;
	
	private Double estimatedIncomeforwhichthisdeclarationismade;
	
	private Double estimatedtotalincomeofthePY;
	
	private Long totalNoofFormNo15Gfiled;
	
	private Double aggregateAmountofincomeforwhichFormNo15Gfiled;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateonwhichDeclarationisreceived;
	
	private String amountofincomepaid;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateonwhichtheincomehasbeenpaidcredited;
	
	private String warning;
	
	private String error;
	
	private String custVendId;
	
	private String accNo;
	
	private String uniqueRefNo;
	
	private String fy;
	
	private String quarter;
	
	private String month;
	
	private Long branchCode;
	
	private String roCode;
	
	private String tan;


	public void setData(JSONObject dataObject) throws ParseException {
		this.uniqueIdentificationNo = dataObject.get("UNIQUEIDENTIFICATIONNO").toString();
		this.identificationNumberofRelevantInvestmentAccount = dataObject.get("IDENTIFICATIONNUMBEROFRELEVANTINVESTMENTACCOUNT").toString();
		this.natureofIncome = dataObject.get("NATUREOFINCOME").toString();
		this.sectionUnderWhichTaxisDeductible = dataObject.get("SECTIONUNDERWHICHTAXISDEDUCTIBLE").toString();
		this.amountofIncome = Double.valueOf(dataObject.get("AMOUNTOFINCOME").toString());
		this.nameofAssesseeDeclarant = dataObject.get("NAMEOFASSESSEEDECLARANT").toString();
		this.panoftheAssessee = dataObject.get("PANOFTHEASSESSEE").toString();
		this.aadhaarNumber = dataObject.get("AADHAARNUMBER").toString();
		this.status = dataObject.get("STATUS").toString();
		this.subStatus = dataObject.get("SUBSTATUS").toString();
		this.previousYearforwhichdeclarationisbeingmade = dataObject.get("PREVIOUSYEARFORWHICHDECLARATIONISBEINGMADE").toString();
		this.residentialStatus = dataObject.get("RESIDENTIALSTATUS").toString();
		this.country = dataObject.get("COUNTRY").toString();
		this.flatDoorBuilding = dataObject.get("FLATDOORBUILDING").toString();
		this.roadStreetBlockSector = dataObject.get("ROADSTREETBLOCKSECTOR").toString();
		this.pincode = Long.valueOf(dataObject.get("PINCODE").toString());
		this.postOffice = dataObject.get("POSTOFFICE").toString();
		this.areaLocality = dataObject.get("AREALOCALITY").toString();
		this.district = dataObject.get("DISTRICT").toString();
		this.state = dataObject.get("STATE").toString();
		this.email = dataObject.get("EMAIL").toString();
		this.telephoneNo = dataObject.get("TELEPHONENO").toString();
		this.mobileNo = dataObject.get("MOBILENO").toString();
		this.whetherAssessedtotaxundertheIncometaxAct1961 = dataObject.get("WHETHERASSESSEDTOTAXUNDERTHEINCOMETAXACT1961").toString();
		this.ifyeslatestassessmentyearforwhichassessed = dataObject.get("IFYESLATESTASSESSMENTYEARFORWHICHASSESSED").toString();
		this.estimatedIncomeforwhichthisdeclarationismade = Double.valueOf(dataObject.get("ESTIMATEDINCOMEFORWHICHTHISDECLARATIONISMADE").toString());
		this.estimatedtotalincomeofthePY = Double.valueOf(dataObject.get("ESTIMATEDTOTALINCOMEOFTHEPY").toString());
		this.totalNoofFormNo15Gfiled = Long.valueOf(dataObject.get("TOTALNOOFFORMNO15GFILED").toString());
		this.aggregateAmountofincomeforwhichFormNo15Gfiled = Double.valueOf(dataObject.get("AGGREGATEAMOUNTOFINCOMEFORWHICHFORMNO15GFILED").toString());
		this.amountofincomepaid = dataObject.get("AMOUNTOFINCOMEPAID").toString();
		this.warning = dataObject.get("WARNING").toString();
		this.error = dataObject.get("ERROR").toString();
		this.custVendId = dataObject.get("CUSTVENDID").toString();
		this.accNo = dataObject.get("ACCNO").toString();
		this.uniqueRefNo = dataObject.get("UNIQUEREFNO").toString();
		this.fy = dataObject.get("FY").toString();
		this.quarter = dataObject.get("QUARTER").toString();
		this.month = dataObject.get("MONTH").toString();
		this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
		this.roCode = dataObject.get("ROCODE").toString();
		this.tan = dataObject.get("TAN").toString();
		
		this.dateonwhichDeclarationisreceived = returnDate(dataObject.get("DATEONWHICHDECLARATIONISRECEIVED").toString());
		this.dateonwhichtheincomehasbeenpaidcredited = returnDate(dataObject.get("DATEONWHICHTHEINCOMEHASBEENPAIDCREDITED").toString());
	}

}
