package domain.in.rjsa.model.form;


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
@Table(name = "AAACU3561B_form.correctionRemarks")
public class CorrectionRemarks extends CommonModelAbstract{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;	
	
	@Column(name = "correctionRequestId")
	public Long correctionRequestId;
	
	@Column(name = "dateTime")
	public String dateTime;
	
	@Column(name = "correctionRemark")
	public String correctionRemark;
	
	@Column(name = "addedBy")
	public String addedBy;
	
	@Column(name = "branchCode")
	public Long branchCode;
	
	@Column(name = "supportingDocName")
	public String supportingDocName;
	
	@Column(name = "remarkStatus")
	public String remarkStatus;
	

}
