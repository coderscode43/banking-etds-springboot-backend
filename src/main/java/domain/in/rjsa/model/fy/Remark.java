package domain.in.rjsa.model.fy;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
@Entity
@Table(name = "FYDetails.deducteeRemark")
public class Remark extends CommonModelAbstract{/**
	 * 
	 */
	private static final long serialVersionUID = 7631763008988100134L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "fy")
	public String fy;
	@Column(name = "deducteeId")
	public Long deducteeId;
	@Column(name = "deducteeForm")
	public String deducteeForm;
	@Column(name = "remark")
	public String remark;
	@Column(name = "dateTime")
	public String dateTime;
	@Column(name = "userName")
	public String userName;
	@Column(name = "branchCode")
	public String branchCode;
	@Column(name = "status")
	public String status;

}
