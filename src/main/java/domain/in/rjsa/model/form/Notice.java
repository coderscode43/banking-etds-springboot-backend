package domain.in.rjsa.model.form;

import java.sql.Date;

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
@Table(name = "AATFR3536K_NOTICE.NOTICES")
public class Notice extends CommonModelAbstract<Notice> {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "FILEID")
	private String FILEID;
	@Column(name = "PAN")
	private String PAN;
	@Column(name = "CLIENTNAME")
	private String CLIENTNAME;
	@Column(name = "SOURCE")
	private String SOURCE;
	@Column(name = "REF_NUMBER")
	private String REF_NUMBER;
	@Column(name = "AY")
	private String AY;
	@Column(name = "PROCEEDING_NAME")
	private String PROCEEDING_NAME;
	@Column(name = "EMAIL")
	private String EMAIL;
	@Column(name = "NOTICESECTION")
	private String NOTICESECTION;	
	@Column(name = "DOCUMENT_ID_NO")
	private String DOCUMENT_ID_NO;
	@Column(name = "ISSUEDON")
	private String ISSUEDON;
	@Column(name = "RESPONSE_DUE_DATE")
	private String RESPONSE_DUE_DATE;
	@Column(name = "FILENAME")
	private String FILENAME;
	@Column(name = "STATUS")
	private String STATUS;
	
}
