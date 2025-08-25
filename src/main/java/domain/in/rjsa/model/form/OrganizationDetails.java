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
//@Table(name = "AAACN4165C_form.organizationDetails")
public class OrganizationDetails extends CommonModelAbstract {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	private Long id;
	//@Column(name = "organizationName")
	public String organizationName;
	//@Column(name = "organizationPan")
	public String organizationPan;
	/*
	 * //@Column(name = "clientId") public Long clientId;
	 */
	
	
}
