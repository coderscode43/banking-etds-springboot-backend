package domain.in.rjsa.model.fy;

import java.text.ParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
//@Entity
//@Table(name = "AAACN4165C_2324.PANUpdateList")
public class PanUpdateList extends CommonModelAbstract<PanUpdateList> {/**
	 * 
	 */
	private static final long serialVersionUID = -3850107842769047020L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
	//@Column(name = "id")
	public Long id;
	//@Column(name = "fy")
	public String fy;
	//@Column(name = "month")
	public String month;
	//@Column(name = "challanHeading")
	public String challanHeading;
	//@Column(name = "custVendId")
	public String custVendId;
	//@Column(name = "previousPAN")
	public String previousPAN;
	//@Column(name = "newPAN")
	public String newPAN;
	//@Column(name = "action")
	public String action;
	//@Column(name = "remark")
	public String remark;
	//@Column(name = "addNewEntry")
	public String addNewEntry;
	
	
    public void setData(JSONObject dataObject) throws ParseException{
        this.fy = dataObject.get("FY").toString();
        this.month = dataObject.get("MONTH").toString();
        this.challanHeading = dataObject.get("CHALLANHEADING").toString();
        this.custVendId = dataObject.get("CUSTVENDID").toString();
        this.previousPAN = dataObject.get("PREVIOUSPAN").toString();
        this.newPAN = dataObject.get("NEWPAN").toString();
        this.action = dataObject.get("ACTION").toString();
        this.remark = dataObject.get("REMARK").toString();
        this.addNewEntry = dataObject.get("ADDNEWENTRY").toString();
    }

}
