package domain.in.rjsa.model.form;

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
@Table(name = "form.fileDetail")
public class FileDetail extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "clientId")
	private Long clientId;
	
	@Column(name = "type")
	private String type;

	@Column(name = "fileName")
	private String fileName;
	@Column(name = "file")
	private byte[] file;

}
