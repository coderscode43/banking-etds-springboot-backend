package domain.in.rjsa.model.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.in.rjsa.util.JsonDateSerializer;
import lombok.Data;

@Data
@Entity
@Table(name = "pensioner")
public class Pensioner extends CommonModelAbstract{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	public Long id;		
	@Column(name = "clientId")
	public Long clientId;
	@Column(name = "pensionNo")
	public String pensionNo;
	@Column(name = "name")
	public String name;
	@Column(name = "fatherName")
	public String fatherName;
	@Column(name = "pan")
	public String pan;
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date dob;
	@Column(name = "empGrade")
	public String empGrade;
	@Column(name = "mobile1")
	public String mobile1;
	@Column(name = "mobile2")
	public String mobile2;
	@Column(name = "email1")
	public String email1;
	@Column(name = "email2")
	public String email2;
	@Column(name = "bankAccNo")
	public String bankAccNo;
	@Column(name = "ifcCode")
	public String ifcCode;
	@Column(name = "bankName")
	public String bankName;
	
	
	@Column(name = "caddrId")
	public Long caddrId;	
	@Column(name = "currentAddress1")
	public String currentAddress1;
	@Column(name = "currentAddress2")
	public String currentAddress2;
	@Column(name = "currentAddress3")
	public String currentAddress3;
	
	@Column(name = "currentcity")
	public String currentcity;
	@Column(name = "currentState")
	public String currentState;
	@Column(name = "currentPincode")
	public String currentPincode;
	
	@Column(name = "paddrId")
	public Long paddrId;
	@Column(name = "permenentAddress1")
	public String permenentAddress1;
	@Column(name = "permenentAddress2")
	public String permenentAddress2;
	@Column(name = "permenentAddress3")
	public String permenentAddress3;
	@Column(name = "permanentCity")
	public String permanentCity;
	@Column(name = "permanentState")
	public String permanentState;
	@Column(name = "permanentPincode")
	public String permanentPincode;
	@Column(name = "bankId")
	public Long bankId;	
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
}
