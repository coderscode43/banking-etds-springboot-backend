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
	public String vendorId;
	@Column(name = "lowerDeduction")
	public String lowerDeduction;
	@Column(name = "cerNo")
	public String cerNo;
	@Column(name = "validFrom")
	public String validFrom;
	@Column(name = "validTo")
	public String validTo;
	@Column(name = "cerLimit")
	public String cerLimit;
	@Column(name = "cerNature")
	public String cerNature;
}
