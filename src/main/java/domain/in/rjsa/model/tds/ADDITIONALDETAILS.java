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
@Table(name = "Taxo.ADDITIONALDETAILS")
public class ADDITIONALDETAILS extends CommonModelAbstract{
    
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "STATENAME")
	public String STATENAME;
	@Column(name = "HASTHESTATEMENTHASBEENFILEDEARLIER")
	public String HASTHESTATEMENTHASBEENFILEDEARLIER;
	@Column(name = "PRN_PREVIOUS")
	public String PRN_PREVIOUS;
	@Column(name = "REMARK")
	public String REMARK;
	@Column(name = "TDSCIRCLE1")
	public String TDSCIRCLE1;
	@Column(name = "TDSCIRCLE2")
	public String TDSCIRCLE2;
	@Column(name = "TDSCIRCLECITY")
	public String TDSCIRCLECITY;
	@Column(name = "TDSCIRCLEPIN")
	public String TDSCIRCLEPIN;
	
	
}
