package domain.in.rjsa.model.fy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.pensionerSalary")
public class PensionerSalary extends CommonModelAbstract{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "employeeId")
	public Long employeeId;
	@Column(name = "pensionersNo")
	@Size(min=0, max=45, message="Pensioners No should be 45 characters.")
	public String pensionersNo;
	@Column(name = "basicSalary")
	@Digits(integer = 12, fraction = 0,message="Enter valid Basic Salary it should not be greater than 12")
	public Double basicSalary;
	@Column(name = "hra")
	@Digits(integer = 12, fraction = 0,message="Enter valid HRA it should not be greater than 12")
	public Double hra;
	@Column(name = "da")
	@Digits(integer = 12, fraction = 0,message="Enter valid DA it should not be greater than 12")
	public Double da;	
	@Column(name = "cca")
	@Digits(integer = 12, fraction = 0,message="Enter valid CCA it should not be greater than 12")
	public Double cca;
	@Column(name = "specialAllowance")
	@Digits(integer = 12, fraction = 0,message="Enter valid Special Allowance it should not be greater than 12")
	public Double specialAllowance;
	@Column(name = "travelAllowance")
	@Digits(integer = 12, fraction = 0,message="Enter valid Travel Allowance it should not be greater than 12")
	public Double travelAllowance;
	@Column(name = "medicalAllowance")
	@Digits(integer = 12, fraction = 0,message="Enter valid Medical Allowance it should not be greater than 12")
	public Double medicalAllowance;
	@Column(name = "epfEmployerContribution")
	@Digits(integer = 12, fraction = 0,message="Enter valid EPF Employer Contribution it should not be greater than 12")
	public Double epfEmployerContribution;
	@Column(name = "epfEmpContribution")
	@Digits(integer = 12, fraction = 0,message="Enter valid EPF Employee Contribution it should not be greater than 12")
	public Double epfEmpContribution;
	@Column(name = "leaveEnhancement")
	@Digits(integer = 12, fraction = 0,message="Enter valid Leave Enhancement it should not be greater than 12")
	public Double leaveEnhancement;
	@Column(name = "horanium")
	@Digits(integer = 12, fraction = 0,message="Enter valid horanium it should not be greater than 12")
	public Double horanium;
	@Column(name = "addIncome")
	@Digits(integer = 12, fraction = 0,message="Enter valid Add Income it should not be greater than 12")
	public Double addIncome;
	@Column(name = "carPerk")
	@Digits(integer = 12, fraction = 0,message="Enter valid Car Perk it should not be greater than 12")
	public Double carPerk;
	@Column(name = "loanPerk")
	@Digits(integer = 12, fraction = 0,message="Enter valid Loan Perk it should not be greater than 12")
	public Double loanPerk;
	@Column(name = "npsEmployerContribution")
	@Digits(integer = 12, fraction = 0,message="Enter valid NPS Employer Contribution it should not be greater than 12")
	public Double npsEmployerContribution;
	@Column(name = "npsEmpContribution")
	@Digits(integer = 12, fraction = 0,message="Enter valid NPS Employee Contribution it should not be greater than 12")
	public Double npsEmpContribution;
	@Column(name = "tds")
	@Digits(integer = 12, fraction = 0,message="Enter valid Tds it should not be greater than 12")
	public Double tds;
	@Column(name = "otherIncome")
	@Digits(integer = 12, fraction = 0,message="Enter valid Other Income it should not be greater than 12")
	public Double otherIncome;
	@Column(name = "month")
	@Size(min=0, max=45, message="Month should be 45 characters.")
	public String month;
	@Column(name = "fy")
	@Digits(integer = 10, fraction = 0,message="Enter valid Other Income it should not be greater than 12")
	public Integer fy;
	

}
