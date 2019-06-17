package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.DeclarationSummary;
import domain.in.rjsa.model.Employee;
import domain.in.rjsa.model.EmployeeInchargeGroup;
import domain.in.rjsa.model.Groups;
import lombok.Data;

@Data
public class EmployeeDetailWrapper {

	
	public Employee emp;
	public Groups group;
	public List<EmployeeInchargeGroup> detailempIncharge;
	public List<DeclarationSummary> decs;
	public List<Groups> groupList;
	public List<Employee> employee;
	public List<EmployeeInchargeGroup> empIncharge;
	public EmployeeInchargeGroup empInchargeGroup;

}
