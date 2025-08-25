package domain.in.rjsa.model.form;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.Remarks")
public class Remark extends CommonModelAbstract<Remark>{
	 
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "ID")
	private Long ID; 
	
	//@Column(name = "DIN")
	private String DIN;
	
	//@Column(name = "PAN")
	private String	PAN; 
	
	//@Column(name = "USERNAME")
	private String USERNAME;
	
	//@Column(name = "REMARK")
	private String REMARK;
	

	
	
}
