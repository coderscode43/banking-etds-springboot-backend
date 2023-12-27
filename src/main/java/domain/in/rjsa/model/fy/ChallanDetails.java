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

import com.fasterxml.jackson.annotation.JsonFormat;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "AAACU3561B_2324.challanDetails")
public class ChallanDetails extends CommonModelAbstract{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	
	@Column(name = "tds")
	public Double tds;
	
	@Column(name = "surcharge")
	public Double surcharge;
	
	@Column(name = "eduCess")
	public Double eduCess;
	
	@Column(name = "interest")
	public Double interest;
	
	@Column(name = "fee")
	public Double fee;
	
	@Column(name = "others")
	public Double others;
	
	@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;
	
	@Column(name = "byBookEntry")
	public String byBookEntry;
	
	@Column(name = "bsrCode")
	public Long bsrCode;
	
	@Column(name = "idNo")
	public Long idNo;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@Column(name = "dateOfDeposition")
	private Date dateOfDeposition;
	
	@Column(name = "minorHeadOfChallan")
	public Double minorHeadOfChallan;
	
	@Column(name = "interestAllocated")
	public Double interestAllocated;
	
	@Column(name = "otherAmtAllocated")
	public Double otherAmtAllocated;
	
	@Column(name = "nilChallanIndicator")
	public String nilChallanIndicator;
	
	@Column(name = "errorDescription")
	public String errorDescription;
	
	@Column(name = "warningDescription")
	public String warningDescription;
	
	@Column(name = "shortPayment")
	public Double shortPayment;
	
	@Column(name = "interestOnShortPayment")
	public Double interestOnShortPayment;
	
	@Column(name = "challanHeading")
	public String challanHeading;
	
	@Column(name = "fy")
	public String fy;
	
	@Column(name = "quarter")
	public String quarter;
	
	@Column(name = "month")
	public String month;
	
	@Column(name = "form")
	public String form;
	
	@Column(name = "TAN")
	public String TAN;

}
