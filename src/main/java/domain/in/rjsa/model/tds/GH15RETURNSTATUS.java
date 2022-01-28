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
@Table(name = "Taxo.GH15RETURNSTATUS")
public class GH15RETURNSTATUS extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "QUARTER")
	public String QUARTER;
	@Column(name = "FORM")
	public String FORM;
	@Column(name = "RT")
	public String RT;
	@Column(name = "PRN")
	public String PRN;
	@Column(name = "STATUS")
	public String STATUS;
	@Column(name = "AS_ON_DATE")
	public String AS_ON_DATE;

}
