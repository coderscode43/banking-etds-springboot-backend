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
@Table(name = "form.branch")
public class Branch extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "tan")
	public String tan;
	@Column(name = "name")
	public String name;
	@Column(name = "branchCode")
	public String branchCode;
	@Column(name = "pan")
	public String pan;
	@Column(name = "type")
	public String type;
	@Column(name = "email")
	public String email;

}
