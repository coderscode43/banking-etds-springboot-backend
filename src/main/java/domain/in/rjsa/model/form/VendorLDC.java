package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "vendorLDC")
public class VendorLDC extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "vendorId")
	public Long vendorId;
	@Column(name = "lowerDeduction")
	@Size(min=0, max=45, message="Lower Deduction value should be 45 characters.")
	@NotNull(message = "Lower Deduction is a required field")
	public String lowerDeduction;
	@Column(name = "cerNo")
	@Size(min=0, max=45, message="Certificate Number should be 45 characters.")
	@NotNull(message = "Certificate Number is a required field")
	public String cerNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public String validFrom;
	@Temporal(TemporalType.DATE)
	@Column(name = "validTo")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public String validTo;
	@Column(name = "cerLimit")
	@Size(min=0, max=45, message="Certificate Limit should be 45 characters.")
	@NotNull(message = "Certificate Limit is a required field")
	public String cerLimit;
	@Column(name = "cerNature")
	@Size(min=0, max=45, message="Certificate Nature should be 45 characters.")
	@NotNull(message = "Certificate Nature is a required field")
	public String cerNature;
}
