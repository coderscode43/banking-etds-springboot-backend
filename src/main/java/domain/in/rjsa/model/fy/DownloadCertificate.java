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
	@Column(name = "customerid")
	public Long customerid;
	@Column(name = "pan")
	public String pan;
	@Column(name = "certificatetype")
	public String certificatetype;
	@Column(name = "financialyear")
	public String financialyear;
	@Column(name = "quarter")
	public String quarter;
	@Column(name = "fileId")
	public Long fileId;
	
		
	
}
