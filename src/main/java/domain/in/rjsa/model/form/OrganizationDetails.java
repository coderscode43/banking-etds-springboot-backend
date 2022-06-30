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
@Table(name = "AABCT5589K_form.organizationDetails")
public class OrganizationDetails extends CommonModelAbstract {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "organizationName")
	public String organizationName;
	@Column(name = "organizationPan")
	public String organizationPan;
	/*
	 * @Column(name = "clientId") public Long clientId;
	 */
	
	
}
