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
@Table(name = "form.userDetails")
public class UserDetails extends CommonModelAbstract{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	Long id; 
	@Column(name = "clientId")
	Long clientId; 
	@Column(name = "name")
	String name;
	@Column(name = "pan")
	String pan;
	@Column(name = "branchCode")
	String branchCode;
	@Column(name = "zone")
	String zone;
	@Column(name = "type")
	String type;
}

