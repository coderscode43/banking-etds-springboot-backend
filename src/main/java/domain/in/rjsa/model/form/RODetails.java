package domain.in.rjsa.model.form;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Data
//@Entity
//@Table(name = "AAACN4165C_form.RODetails")
public class RODetails extends CommonModelAbstract {
	

	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")
		//@Column(name = "id")
		private Long id;
		//@Column(name = "roCode")
		private String roCode;
		//@Column(name = "roName")
		private String roName;
		//@Column(name = "roAddress")
		private String roAddress;
		//@Column(name = "roState")
		private String roState;
		//@Column(name = "roPincode")
		private String roPincode;
		//@Column(name = "roEmail")
		private String roEmail;
		
}
