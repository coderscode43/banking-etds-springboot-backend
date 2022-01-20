package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "form15GH")
public class Form15GH extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	public Long ID;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "AS_ON_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date AS_ON_DATE;

}
