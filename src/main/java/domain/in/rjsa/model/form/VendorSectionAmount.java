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
@Table(name = "vendorSectionAmount")
public class VendorSectionAmount extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "vendorId")
	private Long vendorId;
	@Column(name = "blgId")
	private Long blgId;
	@Column(name = "nopId")
	private Long nopId;
	@Column(name = "fy")
	private String fy;
	@Column(name = "totalAmt")
	private Double totalAmt;
	@Column(name = "totalTDS")
	private Double totalTDS;
	@Column(name = "gstTDSThreshold")
	private Double gstTDSThreshold;
}
