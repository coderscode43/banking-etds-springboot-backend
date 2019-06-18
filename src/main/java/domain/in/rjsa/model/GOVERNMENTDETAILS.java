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
@Table(name = "GOVERNMENTDETAILS")
public class GOVERNMENTDETAILS extends CommonModelAbstract{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "STATENAME")
	public String STATENAME;
	@Column(name = "PAO")
	public String PAO;
	@Column(name = "DDO")
	public String DDO;
	@Column(name = "MINISTRYNAME")
	public String MINISTRYNAME;
	@Column(name = "MINISTRYNAMEOTHER")
	public String MINISTRYNAMEOTHER;
	@Column(name = "PAOREGISTRATIONNUMBER")
	public String PAOREGISTRATIONNUMBER;
	@Column(name = "DDOREGISTRATIONNUMBER")
	public String DDOREGISTRATIONNUMBER;
	@Column(name = "AIN")
	public String AIN;
	
	
	
}
