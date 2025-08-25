package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.G15")
public class G15 extends CommonModelAbstract{
	
	
private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	
	//@Column(name = "id")
	public Long id;		
	
	//@Column(name = "uniqueIdentificationNo")
	public String uniqueIdentificationNo;
	
	//@Column(name = "identificationNumberofRelevantInvestmentAccount")
	private String identificationNumberofRelevantInvestmentAccount;
	
	//@Column(name = "natureofIncome")
	private String natureofIncome;
	
	//@Column(name = "sectionUnderWhichTaxisDeductible")
	public String sectionUnderWhichTaxisDeductible;	
	
	//@Column(name = "amountofIncome")
	public Double amountofIncome;
	
	//@Column(name = "nameofAssesseeDeclarant")
	private String nameofAssesseeDeclarant ;

	//@Column(name = "panoftheAssessee")
	private String panoftheAssessee;
	
	//@Column(name = "aadhaarNumber")
	private String aadhaarNumber;
	
	//@Column(name = "status")
	private String status;
	
	//@Column(name = "subStatus")
	private String subStatus;
	
	//@Column(name = "previousYearforwhichdeclarationisbeingmade")
	private String previousYearforwhichdeclarationisbeingmade;
	
	//@Column(name = "residentialStatus")
	private String residentialStatus;
	
	//@Column(name = "country")
	private String country;
	
	//@Column(name = "flatDoorBuilding")
	private String flatDoorBuilding;
	
	//@Column(name = "roadStreetBlockSector")
	private String roadStreetBlockSector;
	
	//@Column(name = "pincode")
	private Long pincode;
	
	//@Column(name = "postOffice")
	private String postOffice;
	
	//@Column(name = "areaLocality")
	private String areaLocality;
	
	//@Column(name = "district")
	private String district;
	
	//@Column(name = "state")
	private String state;
	
	//@Column(name = "email")
	private String email;
	
	//@Column(name = "telephoneNo")
	private String telephoneNo;
	
	//@Column(name = "mobileNo")
	private String mobileNo;
	
	//@Column(name = "whetherAssessedtotaxundertheIncometaxAct1961")
	private String whetherAssessedtotaxundertheIncometaxAct1961;
	
	//@Column(name = "ifyeslatestassessmentyearforwhichassessed")
	private String ifyeslatestassessmentyearforwhichassessed;
	
	//@Column(name = "estimatedIncomeforwhichthisdeclarationismade")
	private Double estimatedIncomeforwhichthisdeclarationismade;
	
	//@Column(name = "estimatedtotalincomeofthePY")
	private Double estimatedtotalincomeofthePY;
	
	//@Column(name = "totalNoofFormNo15Gfiled")
	private Long totalNoofFormNo15Gfiled;
	
	//@Column(name = "aggregateAmountofincomeforwhichFormNo15Gfiled")
	private Double aggregateAmountofincomeforwhichFormNo15Gfiled;
	
	//@Temporal(TemporalType.DATE)
	//@Column(name = "dateonwhichDeclarationisreceived")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateonwhichDeclarationisreceived;
	
	//@Column(name = "amountofincomepaid")
	private String amountofincomepaid;
	
	//@Temporal(TemporalType.DATE)
	//@Column(name = "dateonwhichtheincomehasbeenpaidcredited")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateonwhichtheincomehasbeenpaidcredited;
	
	//@Column(name = "warning")
	private String warning;
	
	//@Column(name = "error")
	private String error;
	
	//@Column(name = "custVendId")
	private String custVendId;
	
	//@Column(name = "accNo")
	private String accNo;
	
	//@Column(name = "uniqueRefNo")
	private String uniqueRefNo;
	
	//@Column(name = "fy")
	private String fy;
	
	//@Column(name = "quarter")
	private String quarter;
	
	//@Column(name = "month")
	private String month;
	
	//@Column(name = "branchCode")
	private Long branchCode;
	
	//@Column(name = "roCode")
	private String roCode;
	
	//@Column(name = "tan")
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
