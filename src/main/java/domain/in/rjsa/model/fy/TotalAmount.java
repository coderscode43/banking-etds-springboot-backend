package domain.in.rjsa.model.fy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "AAACU3561B_2324.TotalAmount")
public class TotalAmount extends CommonModelAbstract<TotalAmount>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5129286740595140551L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "custVendId")
	public String custVendId;
	@Column(name = "pan")
	public String pan;
	@Column(name = "sectionCode")
	public String sectionCode;
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "month")
	public String month;
	@Column(name = "fy")
	public String fy;
	@Column(name = "totalAmountPaidRaw")
	public Double totalAmountPaidRaw;
	@Column(name = "totalAmountPaidUpload")
	public Double totalAmountPaidUpload;
	@Column(name = "totalTaxRaw")
	public Double totaltaxRaw;
	@Column(name = "totalTaxUploaded")
	public Double totalTaxUploaded;
	@Column(name = "remark")
	public String remark;
	@Column(name = "source")
	public String source;
	
	
	
	
	
	

}
