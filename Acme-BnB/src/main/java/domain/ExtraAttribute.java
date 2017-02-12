
package domain;

import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

public class ExtraAttribute extends DomainEntity {

	//Attributes
	private String	name;
	private boolean	isNumeric;
	private boolean	isNumber;
	private String	value;


	//Constructor
	public ExtraAttribute() {
		super();
	}

	//Getter & setter

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNumeric() {
		return isNumeric;
	}

	public void setNumeric(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

	public boolean isNumber() {
		return isNumber;
	}

	public void setNumber(boolean isNumber) {
		this.isNumber = isNumber;
	}

	@NotBlank
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	//RelationShips

	private Property	property;


	@Valid
	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
