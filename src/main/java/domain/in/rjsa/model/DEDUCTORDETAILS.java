package domain.in.rjsa.model;

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
@Table(name = "DEDUCTORDETAILS")
public class DEDUCTORDETAILS extends CommonModelAbstract{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "FLATORFLOAR")
	public String FLATORFLOAR;
	@Column(name = "BUILDINGNAME")
	public String BUILDINGNAME;
	@Column(name = "ROADSTREET")
	public String ROADSTREET;
	@Column(name = "AREALOCALITY")
	public String AREALOCALITY;
	@Column(name = "CITY")
	public String CITY;
	@Column(name = "STATE")
	public String STATE;
	@Column(name = "PIN")
	public String PIN;
	@Column(name = "STD")
	public String STD;
	@Column(name = "TELEPHONE")
	public String TELEPHONE;
	@Column(name = "COMPANYEMAIL")
	public String COMPANYEMAIL;
	@Column(name = "STD_ALTERNATE")
	public String STD_ALTERNATE;	
	@Column(name = "TELEPHONEALTERNATE")
	public String TELEPHONEALTERNATE;
	@Column(name = "COMPANYEMAILALTERNATE")
	public String COMPANYEMAILALTERNATE;
	@Column(name = "CHANGEADDRESSSINCELASTRETURN")
	public String CHANGEADDRESSSINCELASTRETURN;
	@Column(name = "GSTIN")
	public String GSTIN;
		
}
