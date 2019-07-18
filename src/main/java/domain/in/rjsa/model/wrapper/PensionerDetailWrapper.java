package domain.in.rjsa.model.wrapper;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.form.PensionerSalary;
import lombok.Data;
@Data
public class PensionerDetailWrapper {
	public Pensioner pensioner;
	public Address paddress;
	public Address caddress;
	public BankAccDetail bank;
	public PensionerSalary psalary;
	
	}
	
	
