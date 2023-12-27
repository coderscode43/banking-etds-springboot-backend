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
@Table(name = "AAACU3561B_form.correctionRequestAmountDetails")
public class CorrectionRequestAmountDetails extends CommonModelAbstract{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfPayment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dateOfPayment;
	@Column(name = "amountPaid")
	public Long amountPaid;
	@Column(name = "tdsDeducted")
	public Long tdsDeducted;
	@Column(name = "tds26as")
	public Long tds26as;
	@Column(name = "amountPaid26as")
	public Long amountPaid26as;
	@Column(name = "remark")
	public String remark;
	@Column(name = "correctionRequestId")
	public Long correctionRequestId;
	
	
	

}
