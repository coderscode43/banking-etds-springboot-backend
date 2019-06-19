package domain.in.rjsa.model;

import java.util.Date;

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
@Table(name = "LDC")
public class LDC extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "LDC_NUMBER")
	public String LDC_NUMBER;
	@Column(name = "NAME")
	public String NAME;
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "PAN")
	public String PAN;
	@Column(name = "FY")
	public String FY;	
	@Column(name = "VALID_FROM")
	public String VALID_FROM;
	@Column(name = "VALID_TO")
	public String VALID_TO;
	@Column(name = "SECTION_CODE")
	public String SECTION_CODE;
	@Column(name = "NATURE_OF_PAYMENT")
	public String NATURE_OF_PAYMENT;
	@Column(name = "LDC_RATE")
	public String LDC_RATE;
	@Column(name = "CERTIFICATE_LIMIT")
	public String CERTIFICATE_LIMIT;
	@Column(name = "AMOUNT_CONSUMED")
	public String AMOUNT_CONSUMED;
	@Column(name = "ISSUE_DATE")
	public String ISSUE_DATE;
	@Column(name = "CANCEL_DATE")
	public String CANCEL_DATE;
	@Column(name = "AS_ON_DATE")
	public Date AS_ON_DATE;
}
