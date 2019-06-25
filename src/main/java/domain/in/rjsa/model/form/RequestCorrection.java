package domain.in.rjsa.model.form;

import java.util.Date;

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
@Table(name = "requestCorrection")
public class RequestCorrection extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "branchId")
	public Long branchId;
	@Column(name = "form")
	public String form;
	@Column(name = "quarter")
	public String quarter;
	@Column(name = "fy")
	public String fy;
	@Column(name = "dor")
	public Date dor;
	@Column(name = "status")
	public String status;
	@Column(name = "remark")
	public String remark;
}
