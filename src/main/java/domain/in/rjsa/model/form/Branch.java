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
@Table(name = "AAACU3561B_form.branch")
public class Branch extends CommonModelAbstract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	@Column(name = "roCode")
	private String roCode;
	@Column(name = "branchCode")
	public Long branchCode;
	@Column(name = "branchName")
	public String branchName;
	@Column(name = "branchEmail")
	public String branchEmail;
	@Column(name = "branchContactNo")
	public String branchContactNo;
	@Column(name = "branchAddress")
	public String branchAddress;
	@Column(name = "branchPinCode")
	public String branchPinCode;
	@Column(name = "branchState")
	public String branchState;

}
