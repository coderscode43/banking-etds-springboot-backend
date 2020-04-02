package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Salary;
import lombok.Data;
@Data
public class EmployeeDetailWrapper {
	public BankAccDetail bank;
	//public List <BankAccDetail> bService;
	public Address paddress;
	public Address taddress;
	
	public Salary salary;
	public void setVpList(List<BankAccDetail> vendorPay) {
		// TODO Auto-generated method stub
		
	}
	
	
}
