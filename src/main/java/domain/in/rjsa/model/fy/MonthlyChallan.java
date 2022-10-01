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
@Table(name = "FYDetails.monthlyChallan1")
public class MonthlyChallan extends CommonModelAbstract{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "branchCode")
	public Long	 branchCode;
	@Column(name = "month")
	public String month;
	@Column(name = "amtAsPerFinacle")
	public Double amtAsPerFinacle;
	@Column(name = "amtAsPerTaxCalculation")
	public Double amtAsPerTaxCalculation;
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "fy")
	public String fy;
	@Column(name = "amountDiff")
	public Double amountDiff;
	@Column(name = "remarks")
	public String remarks;
	

}
