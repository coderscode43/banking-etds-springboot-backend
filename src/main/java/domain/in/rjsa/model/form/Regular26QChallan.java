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
@Table(name = "regular26QChallan")
public class Regular26QChallan extends CommonModelAbstract{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "srNo")
	public Long srNo;
	@Column(name = "tds")
	public Double tds;
	@Column(name = "surcharge")
	public Double surcharge;
	@Column(name = "eduCess")
	public Long eduCess;
	@Column(name = "interest")
	public Long interest;
	@Column(name = "fee")
	public Long fee;
	@Column(name = "others")
	public Long others;
	@Column(name = "totalTaxDeposit")
	public Long totalTaxDeposit;
	@Column(name = "bsrCode")
	public String bsrCode;
	@Column(name = "challanSrNo")
	public Long challanSrNo;
	@Column(name = "ddoSrNo")
	public Long ddoSrNo;
    @Temporal(TemporalType.DATE)
	@Column(name = "dateTaxDeposit")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dateTaxDeposit;
	@Column(name = "minorHeadChallan")
	public String minorHeadChallan;
	@Column(name = "interestAllocated")
	public Long interestAllocated;
	@Column(name = "otherAmtAllocated")
	public Long otherAmtAllocated;
	@Column(name = "nilChallanIndicator")
	public String nilChallanIndicator;
	@Column(name = "fy")
	public String fy;
	@Column(name = "amountConsumed")
	public Long amountConsumed;
	@Column(name = "amountAvailable")
	public Long amountAvailable;
	@Column(name = "verify")
	public String verify;
	

}
