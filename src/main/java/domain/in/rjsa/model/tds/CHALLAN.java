package domain.in.rjsa.model.tds;

import java.sql.Date;

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
@Table(name = "Taxo.CHALLAN")
public class CHALLAN extends CommonModelAbstract{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "CIN")
	public String CIN;	
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "AVAILABLE_BALENCE")
	public String AVAILABLE_BALENCE;
	@Column(name = "AMOUNT_OF_CLALLAN")
	public String AMOUNT_OF_CLALLAN;
	@Column(name = "DATE_OF_DEPOSITION")
	public Date DATE_OF_DEPOSITION;
	@Column(name = "AS_ON_DATE")
	public Date AS_ON_DATE;
	@Column(name = "CHALLAN_MISMATCH")
	public String CHALLAN_MISMATCH;
	@Column(name = "SECTION")
	public String SECTION;
	@Column(name = "MINORHEAD")
	public String MINORHEAD;
	
}
