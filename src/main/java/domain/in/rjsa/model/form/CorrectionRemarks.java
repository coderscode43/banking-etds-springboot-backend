package domain.in.rjsa.model.form;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_form.correctionRemarks")
public class CorrectionRemarks extends CommonModelAbstract{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452993067071903984L;
	//@Id
	//@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;	
	
	//@Column(name = "correctionRequestId")
	public Long correctionRequestId;
	
	//@Column(name = "dateTime")
	public String dateTime;
	
	//@Column(name = "correctionRemark")
	public String correctionRemark;
	
	//@Column(name = "addedBy")
	public String addedBy;
	
	//@Column(name = "branchCode")
	public Long branchCode;
	
	//@Column(name = "supportingDocName")
	public String supportingDocName;
	
	//@Column(name = "remarkStatus")
	public String remarkStatus;
	

}
