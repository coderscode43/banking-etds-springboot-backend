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
@Table(name = "form.RODetails")
public class RODetails extends CommonModelAbstract {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
		@Column(name = "id")
		private Long id;
		@Column(name = "roCode")
		private String roCode;
		@Column(name = "roName")
		private String roName;
		@Column(name = "roAddress")
		private String roAddress;
		@Column(name = "roState")
		private String roState;
		@Column(name = "roPincode")
		private Long roPincode;
		@Column(name = "roEmail")
		private String roEmail;
		
}
