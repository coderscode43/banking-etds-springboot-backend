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
@Table(name = "AAACU3561B_form.zonalBranches")
public class ZonalBranches extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	@Column(name = "clientId")
	public Long clientId;	
	@Column(name = "zonalId")
	public Long zonalId;
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "zoneCode")
	public String zoneCode;
	@Column(name = "branchCode")
	public Long branchCode;
}
