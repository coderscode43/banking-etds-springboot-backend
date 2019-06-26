package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Employee;
import lombok.Data;
@Data
public class EmployeeDetailWrapper {
	public BankAccDetail bank;
	public Address paddress;
	public Address taddress;
	public Employee employee;
	
}
