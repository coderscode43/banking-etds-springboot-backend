package domain.in.rjsa.model;

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
@Table(name = "DEFAULTSUMMARY")
public class DEFAULTSUMMARY extends CommonModelAbstract{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	public String ID;
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "FY")
	public String FY;
	@Column(name = "QUARTER")
	public String QUARTER;
	@Column(name = "FORM")
	public String FORM;
	@Column(name = "STATUS")
	public String STATUS;
	@Column(name = "AS_ON_DATE")
	public String AS_ON_DATE;
	@Column(name = "NET_PAY")
	public String NET_PAY;
	@Column(name = "TOTAL")
	public String TOTAL;
	@Column(name = "SHORT_PAYMENT")
	public String SHORT_PAYMENT;
	@Column(name = "INTEREST_SHORT_PAYMENT")
	public String INTEREST_SHORT_PAYMENT;
	@Column(name = "SHORT_DEDUCTION")
	public String SHORT_DEDUCTION;
	@Column(name = "INTEREST_SHORT_DEDUCTION")
	public String INTEREST_SHORT_DEDUCTION;
	@Column(name = "ADDITIONAL_LATE_PAYMENT")
	public String ADDITIONAL_LATE_PAYMENT;
	@Column(name = "INTEREST_ON_LATE_PAYMENT")
	public String INTEREST_ON_LATE_PAYMENT;
	@Column(name = "LATE_FILING")
	public String LATE_FILING;
	@Column(name = "INTERST_LATE_DEDUCTION")
	public String INTERST_LATE_DEDUCTION;
	@Column(name = "INTERST")
	public String INTERST;
	@Column(name = "ADDITIONAL_LATE_FILING")
	public String ADDITIONAL_LATE_FILING;
	@Column(name = "ADDITIONAL_LATE_DEDUCTION")
	public String ADDITIONAL_LATE_DEDUCTION;
	@Column(name = "ORDER_PASSED_DATE")
	public String ORDER_PASSED_DATE;
	@Column(name = "DEDUCTEES_WITHOUT_PAN")
	public String DEDUCTEES_WITHOUT_PAN;
	@Column(name = "DEDUCTEES_WITH_INVALID_PAN")
	public String DEDUCTEES_WITH_INVALID_PAN;
	
}
