package domain.in.rjsa.model.fy;

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
@Table(name = "FYDetails.downloadCertificate")
public class DownloadCertificate extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "clientId")
	private Long clientId;
	@Column(name = "customer_id")
	public Long customer_id;
	@Column(name = "pan")
	public String pan;
	@Column(name = "certificate_type")
	public String certificate_type;
	@Column(name = "financial_year")
	public String financial_year;
	@Column(name = "quarter")
	public String quarter;
	@Column(name = "fileId")
	public Long fileId;
	
		
	
}
