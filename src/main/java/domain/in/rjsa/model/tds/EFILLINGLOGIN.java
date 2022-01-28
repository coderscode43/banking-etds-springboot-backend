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
@Table(name = "Taxo.EFILLINGLOGIN")
public class EFILLINGLOGIN extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "TAN")
	public String TAN;
	@Column(name = "USERNAME")
	public String USERNAME;
	@Column(name = "PASSWORD")
	public String PASSWORD;
}
