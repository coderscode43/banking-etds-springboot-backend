package domain.in.rjsa.model.fy;

import java.text.ParseException;

import org.json.simple.JSONObject;

import domain.in.rjsa.model.form.CommonModelAbstract;
import lombok.Data;

@Data
public class PanUpdateList extends CommonModelAbstract<PanUpdateList> {/**
	 * 
	 */
	private static final long serialVersionUID = -3850107842769047020L;
	public Long id;
	public String fy;
	public String month;
	public String challanHeading;
	public String custVendId;
	public String previousPAN;
	public String newPAN;
	public String action;
	public String remark;
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
