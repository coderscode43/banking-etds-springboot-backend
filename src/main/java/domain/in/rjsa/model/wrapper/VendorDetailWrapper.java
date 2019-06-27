package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Vendor;
import domain.in.rjsa.model.form.VendorLDC;
import domain.in.rjsa.model.form.VendorPayment;
import lombok.Data;
@Data
public class VendorDetailWrapper {
	public Vendor vendor;
	public List<VendorLDC> vldcs;
	public List<VendorPayment> vpList;
	public BankAccDetail bank;
	public Address address;
	
}
