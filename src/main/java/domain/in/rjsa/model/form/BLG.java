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
@Table(name = "blg")
public class BLG extends CommonModelAbstract{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "section")
	private String section;
	@Column(name = "blgCode")
	private String blgCode;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "threshold")
	private Double threshold;
	@Column(name = "rate")
	private Double rate;
	@Column(name = "gstTDSThreshold")
	private Double gstTDSThreshold;

}
