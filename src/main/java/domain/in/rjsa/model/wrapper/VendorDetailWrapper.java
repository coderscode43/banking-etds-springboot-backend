package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.fy.VendorLDC;
import domain.in.rjsa.model.fy.VendorPayment;
import lombok.Data;
@Data
public class VendorDetailWrapper {
	
	public VendorLDC vendorLDC;
	public List<VendorLDC> vldcs;
	public List<VendorPayment> vpList;
	public BankAccDetail bank;
	public Address address;
	public VendorPayment vendorPayment;
	
}
