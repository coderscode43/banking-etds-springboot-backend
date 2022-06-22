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
@Table(name = "AATFR3536K_NOTICE.Remarks")
public class Remark extends CommonModelAbstract<Remark>{
	 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long ID; 
	
	@Column(name = "DIN")
	private String DIN;
	
	@Column(name = "PAN")
	private String	PAN; 
	
	@Column(name = "USERNAME")
	private String USERNAME;
	
	@Column(name = "REMARK")
	private String REMARK;
	

	
	
}
