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
@Table(name = "FYDetails.misReport")
public class MisReport extends CommonModelAbstract{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "branchCode")
	public String branchCode;
	@Column(name = "fromDate")
	private String fromDate;
	@Column(name = "toDate")
	private String toDate;
	@Column(name = "reportType")
	public String reportType;	
	@Column(name = "fy")
	public String fy;
	@Column(name = "userName")
	public String userName;
	
	
	

}
