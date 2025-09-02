package domain.in.rjsa.model.fy;

import java.text.ParseException;
import java.util.Date;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class H15 extends CommonModelAbstract {

	private static final long serialVersionUID = 1L;

	public Long id;

	public String uniqueIdentificationNo;

	private String identificationNumberofRelevantInvestmentAccount;

	private String natureofIncome;

	public String sectionUnderWhichTaxisDeductible;

	public Double amountofIncome;

	private String nameofAssesseeDeclarant;

	private String panoftheAssessee;

	private String aadhaarNumberoftheAssessee;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateofBirth;

	private String previousYearforwhichdeclarationisbeingmade;

	private String country;

	private String flatDoorBuilding;

	private String roadStreetBlockSector;

	private Long pincode;

	private String postOffice;

	private String areaLocality;

	private String district;

	private String state;

	private String email;

	private Long STDCode;

	private String telephoneNo;

	private String mobileNo;

	private String whetherAssessedtotax;

	private String latestassessmentyearforwhichassessed;

	private Double estimatedtotalincomeofthePY;

	private Double estimatedIncomeforwhichthisdeclarationismade;

	private Long totalNoofFormNo15Hfiled;

	public Double aggregateAmountofincomeforwhichFormNo15Hfiled;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date dateonwhichDeclarationisreceived;

	private Double amountofincomepaid;

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

	private String tan;

	private String roCode;

	public void setData(JSONObject dataObject) throws ParseException {
		this.uniqueIdentificationNo = dataObject.get("UNIQUEIDENTIFICATIONNO").toString();
		this.identificationNumberofRelevantInvestmentAccount = dataObject
				.get("IDENTIFICATIONNUMBEROFRELEVANTINVESTMENTACCOUNT").toString();
		this.natureofIncome = dataObject.get("NATUREOFINCOME").toString();
		this.sectionUnderWhichTaxisDeductible = dataObject.get("SECTIONUNDERWHICHTAXISDEDUCTIBLE").toString();
		this.amountofIncome = Double.valueOf(dataObject.get("AMOUNTOFINCOME").toString());
		this.nameofAssesseeDeclarant = dataObject.get("NAMEOFASSESSEDECLARANT").toString();
		this.panoftheAssessee = dataObject.get("PANOFTHASSESSEE").toString();
		this.aadhaarNumberoftheAssessee = dataObject.get("AADHAARNUMBEROFTHEASSESSEE").toString();

		this.dateofBirth = returnDate(dataObject.get("DATEOFBIRTH").toString());
		this.dateonwhichDeclarationisreceived = returnDate(
				dataObject.get("DATEONWHICHDECLARATIONISRECEIVED").toString());
		this.dateonwhichtheincomehasbeenpaidcredited = returnDate(
				dataObject.get("DATEONWHICHTHEINCOMEHASBEENPAIDCREDITED").toString());

		this.previousYearforwhichdeclarationisbeingmade = dataObject.get("PREVIOUSYEARFORWHICHDECLARATIONISBEINGMADE")
				.toString();
		this.country = dataObject.get("COUNTRY").toString();
		this.flatDoorBuilding = dataObject.get("FLATDOORBUIDLING").toString();
		this.roadStreetBlockSector = dataObject.get("ROADSTREETBLOCKSECTOR").toString();
		this.pincode = Long.valueOf(dataObject.get("PINCODE").toString());
		this.postOffice = dataObject.get("POSTOFFICE").toString();
		this.areaLocality = dataObject.get("AREALOCALITY").toString();
		this.district = dataObject.get("DISTRICT").toString();
		this.state = dataObject.get("STATE").toString();
		this.email = dataObject.get("EMAIL").toString();
		this.STDCode = Long.valueOf(dataObject.get("STDCODE").toString());
		this.telephoneNo = dataObject.get("TELEPHONENO").toString();
		this.mobileNo = dataObject.get("MOBILENO").toString();
		this.whetherAssessedtotax = dataObject.get("WHETHERASSESSEDTOTAX").toString();
		this.latestassessmentyearforwhichassessed = dataObject.get("LATESTASSESSMENTYEARFORWHICHASSESSED").toString();
		this.estimatedtotalincomeofthePY = Double.valueOf(dataObject.get("ESTIMATEDTOTALINCOMEOFTHEPY").toString());
		this.estimatedIncomeforwhichthisdeclarationismade = Double
				.valueOf(dataObject.get("ESTIMATEDINCOMEFORWHICHTHISDECLARATIONISMADE").toString());
		this.totalNoofFormNo15Hfiled = Long.valueOf(dataObject.get("TOTALNOOFFORMNO15HFILED").toString());
		this.aggregateAmountofincomeforwhichFormNo15Hfiled = Double
				.valueOf(dataObject.get("AGGREGATEAMOUNTOFINCOMEFORWHICHFORMNO15HFILED").toString());

		this.amountofincomepaid = Double.valueOf(dataObject.get("AMOUNTOFINCOMEPAID").toString());
		this.warning = dataObject.get("WARNING").toString();
		this.error = dataObject.get("ERROR").toString();
		this.custVendId = dataObject.get("CUSTVENDID").toString();
		this.accNo = dataObject.get("ACCNO").toString();
		this.uniqueRefNo = dataObject.get("UNIQUEREFNO").toString();
		this.fy = dataObject.get("FY").toString();
		this.quarter = dataObject.get("QUARTER").toString();
		this.month = dataObject.get("MONTH").toString();
		this.branchCode = Long.valueOf(dataObject.get("BRANCHCODE").toString());
		this.tan = dataObject.get("TAN").toString();
		this.roCode = dataObject.get("ROCODE").toString();
	}

}
