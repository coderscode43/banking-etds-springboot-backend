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
@Table(name = "vendor")
public class Vendor extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "vendorNo")
	public String vendorNo;
	@Column(name = "vendorName")
	public String vendorName;
	
	@Column(name = "pan")
	public String pan;
	
	@Column(name = "gstNo")
	public String gstNo;
	@Column(name = "resiStatus")
	public String resiStatus;
	@Column(name = "telNo")
	public String telNo;
	@Column(name = "mobNo")
	public String mobNo;
	@Column(name = "email")
	public String email;
	
	
	@Column(name = "address1")
	public String address1;
	@Column(name = "address2")
	public String address2;
	@Column(name = "address3")
	public String address3;
	@Column(name = "city")
	public String city;
	@Column(name = "state")
	public String state;
	@Column(name = "pincode")
	public String pincode;
	@Column(name = "contPerName")
	public String contPerName;
	@Column(name = "bankAccNo")
	public String bankAccNo;
	@Column(name = "ifcCode")
	public String ifcCode;
	@Column(name = "bankName")
	public String bankName;
	@Column(name = "lowerDeduction")
	public String lowerDeduction;
	@Column(name = "cerNo")
	public String cerNo;
	@Column(name = "validFrom")
	public String validFrom;
	@Column(name = "validTo")
	public String validTo;
	@Column(name = "cerLimit")
	public String cerLimit;
	@Column(name = "cerNature")
	public String cerNature;
	
	
  }
