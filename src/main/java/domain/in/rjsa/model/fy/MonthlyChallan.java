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
@Table(name = "FYDetails.monthlyChallan")
public class MonthlyChallan extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "sectionCode")
	public Long sectionCode;	
	@Column(name = "branchCode")
	public String branchCode;
	@Column(name = "month")
	public String month;
	@Column(name = "amtAsPerFinacle")
	public Long amtAsPerFinacle;
	@Column(name = "amtAsPerTaxCalculation")
	public Long amtAsPerTaxCalculation;
	@Column(name = "challanHeading")
	public String challanHeading;

}
