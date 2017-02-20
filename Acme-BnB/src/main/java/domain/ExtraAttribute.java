
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class ExtraAttribute extends DomainEntity {

	//Attributes
	private String	name;
	private boolean	isNumber;
	private boolean	isBoolean;
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

	public boolean getIsNumber() {
		return isNumber;
	}

	public void setIsNumber(boolean isNumber) {
		this.isNumber = isNumber;
	}

	public boolean getIsBoolean() {
		return isBoolean;
	}

	public void setIsBoolean(boolean isBoolean) {
		this.isBoolean = isBoolean;
	}

	@NotBlank
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	//RelationShips

	private Collection<Property>	properties;


	@Valid
	@ManyToMany
	@JoinTable(name = "property_extraAttribute", joinColumns = @JoinColumn(name = "extraAttribute_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"))
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

}
