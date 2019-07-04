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
@Table(name = "regular24Q4Deductee")
public class Regular24Q4Deductee extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	@Column(name = "employeeRefNo")
	public String employeeRefNo;
	@Column(name = "panRefNo")
	public String panRefNo;
	@Column(name = "pan")
	public String pan;
	@Column(name = "name")
	public String name;
	@Column(name = "sectionCode")
	public String sectionCode;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateOfPayment;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfDeduction")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateOfDeduction;
	@Column(name = "amountPaid")
	public Double amountPaid;
	@Column(name = "tds")
	public Double tds;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "educationCess")
	public Double educationCess;
	@Column(name = "totalTds")
	public String totalTds;
	@Column(name = "totalTaxDeposited")
	public Double totalTaxDeposited;
	@Column(name = "certificateNumber")
	public String certificateNumber;
	@Column(name = "remarks")
	public String remarks;
	@Column(name = "fy")
	public String fy;
	@Column(name = "quarter")
	public String quarter;
}
