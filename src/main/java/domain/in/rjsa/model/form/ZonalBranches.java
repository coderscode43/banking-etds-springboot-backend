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
//@Table(name = "AAACN4165C_form.zonalBranches")
public class ZonalBranches extends CommonModelAbstract{
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;	
	//@Column(name = "clientId")
	public Long clientId;	
	//@Column(name = "zonalId")
	public Long zonalId;
	//@Column(name = "branchId")
	public Long branchId;
	//@Column(name = "zoneCode")
	public String zoneCode;
	//@Column(name = "branchCode")
	public Long branchCode;
}
