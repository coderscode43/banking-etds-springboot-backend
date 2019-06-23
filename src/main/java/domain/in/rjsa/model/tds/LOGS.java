package domain.in.rjsa.model.tds;

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
@Table(name = "regular24Q4Challan")
public class LOGS extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	public String ID;
	@Column(name = "USER")
	public String USER;
	@Column(name = "DATE")
	public String DATE;
	@Column(name = "ACTION")
	public String ACTION;
	
	@Column(name = "FY")
	public String FY;
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "FORM")
	public String FORM;
	
	@Column(name = "QUARTER")
	public String QUARTER;
	@Column(name = "TIME")
	public String TIME;
	

}
