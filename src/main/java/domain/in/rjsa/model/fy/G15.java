package domain.in.rjsa.model.fy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.G15")
public class G15 extends CommonModelAbstract{
	
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	
	@Column(name = "id")
	public Long id;		
	@Column(name = "uniqueIdentificationNo")
	public String uniqueIdentificationNo;
	
	@Column(name = "identificationNumberofRelevantInvestmentAccount")
	private String identificationNumberofRelevantInvestmentAccount;
	
	@Column(name = "natureofIncome")
	private String natureofIncome;
	
	@Column(name = "sectionUnderWhichTaxisDeductible")
	public String sectionUnderWhichTaxisDeductible;	
	
	@Column(name = "amountofIncome")
	public Double amountofIncome;
	
	@Column(name = "nameofAssesseeDeclarant")
	private String nameofAssesseeDeclarant ;

	@Column(name = "panoftheAssessee")
	private String panoftheAssessee;
	
	@Column(name = "aadhaarNumber")
	private String aadhaarNumber;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "subStatus")
	private String subStatus;
	
	@Column(name = "previousYearforwhichdeclarationisbeingmade")
	private String previousYearforwhichdeclarationisbeingmade;
	
	@Column(name = "residentialStatus")
	private String residentialStatus;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "flatDoorBuilding")
	private String flatDoorBuilding;
	
	@Column(name = "roadStreetBlockSector")
	private String roadStreetBlockSector;
	
	@Column(name = "pincode")
	private Long pincode;
	
	@Column(name = "postOffice")
	private String postOffice;
	
	@Column(name = "areaLocality")
	private String areaLocality;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephoneNo")
	private String telephoneNo;
	
	@Column(name = "mobileNo")
	private String mobileNo;
	
	@Column(name = "whetherAssessedtotaxundertheIncometaxAct1961")
	private String whetherAssessedtotaxundertheIncometaxAct1961;
	
	@Column(name = "ifyeslatestassessmentyearforwhichassessed")
	private String ifyeslatestassessmentyearforwhichassessed;
	
	@Column(name = "estimatedIncomeforwhichthisdeclarationismade")
	private Double estimatedIncomeforwhichthisdeclarationismade;
	
	@Column(name = "estimatedtotalincomeofthePY")
	private Double estimatedtotalincomeofthePY;
	
	@Column(name = "totalNoofFormNo15Gfiled")
	private Long totalNoofFormNo15Gfiled;
	
	@Column(name = "aggregateAmountofincomeforwhichFormNo15Gfiled")
	private Double aggregateAmountofincomeforwhichFormNo15Gfiled;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateonwhichDeclarationisreceived")
	private Date dateonwhichDeclarationisreceived;
	
	@Column(name = "amountofincomepaid")
	private String amountofincomepaid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateonwhichtheincomehasbeenpaidcredited")
	private Date dateonwhichtheincomehasbeenpaidcredited;
	
	@Column(name = "warning")
	private String warning;
	
	@Column(name = "error")
	private String error;
	
	@Column(name = "custVendId")
	private String custVendId;
	
	@Column(name = "accNo")
	private String accNo;
	
	@Column(name = "uniqueRefNo")
	private String uniqueRefNo;
	
	@Column(name = "fy")
	private String fy;
	
	@Column(name = "quarter")
	private String quarter;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "branchCode")
	private String branchCode;
	
	@Column(name = "tan")
	private String tan;


}
