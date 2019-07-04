package domain.in.rjsa.model.form;

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

import lombok.Data;

@Data
@Entity
@Table(name = "regular24Q4Challan")
public class Regular24Q4Challan extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "clientId")
	public Long clientId;	
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "tds")
	public Double tds;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	public Double educationCess;
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
	@Column(name = "challanSerialNo")
	public Long challanSerialNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeposition")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateOfDeposition;
	@Column(name = "minorHeadOfChallan")
	public String minorHeadOfChallan;
	@Column(name = "interestAllocated")
	public Double interestAllocated;
	@Column(name = "otherAmtAllocated")
	public Double otherAmtAllocated;
	@Column(name = "remarks")
	public String remarks;
	@Column(name = "nillChallanIndicator")
	public String nillChallanIndicator;
	@Column(name = "errorDescription")
	public String errorDescription;
	@Column(name = "warningDescription")
	public String warningDescription;
	@Column(name = "fy")
	public String fy;
	@Column(name = "quarter")
	public String quarter;
	
}
