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
@Table(name = "form.address")
public class Address extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "address1")
	public String address1;
	@Column(name = "address2")
	public String address2;
	@Column(name = "address3")
	public String address3;
	@Column(name = "city")
	public String city;
	@Column(name = "state")
	public String state;
	@Column(name = "pincode")
	public String pincode;
	
	
}
