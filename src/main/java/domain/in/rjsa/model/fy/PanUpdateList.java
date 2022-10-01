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
@Table(name = "FYDetails.PANUpdateList")

public class PanUpdateList extends CommonModelAbstract<PanUpdateList> {/**
	 * 
	 */
	private static final long serialVersionUID = -3850107842769047020L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;
	@Column(name = "fy")
	public String fy;
	@Column(name = "month")
	public String month;
	@Column(name = "challanHeading")
	public String challanHeading;
	@Column(name = "custVendId")
	public String custVendId;
	@Column(name = "previousPAN")
	public String previousPAN;
	@Column(name = "newPAN")
	public String newPAN;
	@Column(name = "action")
	public String action;
	@Column(name = "remark")
	public String remark;
	@Column(name = "addNewEntry")
	public String addNewEntry;
	
	
	
	
	

}
