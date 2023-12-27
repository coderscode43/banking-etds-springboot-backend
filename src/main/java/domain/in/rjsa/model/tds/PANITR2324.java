package domain.in.rjsa.model.tds;

import java.util.Date;

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
@Table(name = "Taxo.PANITR2324")
public class PANITR2324 extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PAN")
	public String PAN;
	@Column(name = "PANDetail")
	public String PANDetail;
	@Column(name = "PANVerifiedDate")
	public Date PANVerifiedDate;
}
