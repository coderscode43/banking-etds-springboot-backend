package domain.in.rjsa.model;

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
@Table(name = "TOKENNUMBER")
public class TOKENNUMBER extends CommonModelAbstract{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	public Long ID;
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "FY")
	public String FY;
	@Column(name = "QUARTER")
	public String QUARTER;
	@Column(name = "FORM")
	public String FORM;
	@Column(name = "DATEOFFILING")
	public String DATEOFFILING;
	@Column(name = "PRN")
	public String PRN;
	@Column(name = "RETURNTYPE")
	public String RETURNTYPE;
	
	
}
