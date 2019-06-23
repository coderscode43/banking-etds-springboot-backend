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
@Table(name = "vendor")
public class Vendor {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "name")
	public String name;
	
	@Column(name = "vendorCode")
	public String vendorCode;

	@Column(name = "pan")
	public String pan;
	@Column(name = "address")
	public String address;
	@Column(name = "mobile")
	public String mobile;
	@Column(name = "email")
	public String email;
}
