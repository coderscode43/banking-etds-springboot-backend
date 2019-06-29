package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "pensionersalary")
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
	@Column(name = "basicSalary")
	public Double basicSalary;
	@Column(name = "hra")
	public Double hra;
	@Column(name = "da")
	public Double da;	
	@Column(name = "cca")
	public Double cca;
	@Column(name = "specialAllowance")
	public Double specialAllowance;
	@Column(name = "travelAllowance")
	public Double travelAllowance;
	@Column(name = "medicalAllowance")
	public Double medicalAllowance;
	@Column(name = "epfEmployerContribution")
	public Double epfEmployerContribution;
	@Column(name = "epfEmpContribution")
	public Double epfEmpContribution;
	@Column(name = "leaveEnhancement")
	public Double leaveEnhancement;
	@Column(name = "horanium")
	public Double horanium;
	@Column(name = "addIncome")
	public Double addIncome;
	@Column(name = "carPerk")
	public Double carPerk;
	@Column(name = "loanPerk")
	public Double loanPerk;
	@Column(name = "npsEmployerContribution")
	public Double npsEmployerContribution;
	@Column(name = "npsEmpContribution")
	public Double npsEmpContribution;
	@Column(name = "tds")
	public Double tds;
	@Column(name = "otherIncome")
	public Double otherIncome;
	@Column(name = "month")
	public String month;
	

}
