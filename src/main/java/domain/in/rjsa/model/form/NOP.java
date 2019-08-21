package domain.in.rjsa.model.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "nop")
public class NOP extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "threshold")
	private Double threshold;
	@Column(name = "section")
	private String section;
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "gstTDSRate")
	private Double gstTDSRate;
	
	@Column(name = "natureOfPayment")
	public String natureOfPayment;
	@Column(name = "gstTDSThreshold")
	private Double gstTDSThreshold;
	
}
