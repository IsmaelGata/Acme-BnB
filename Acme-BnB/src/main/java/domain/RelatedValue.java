
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(name = "Unicidad_ExtraAttribute_Property", columnNames = {
		"extraAttribute_id", "property_id"
	})
})
public class RelatedValue extends DomainEntity {

	//Attributes

	private String	value;


	//Constructor

	public RelatedValue() {
		super();
	}

	//Getter & setter

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	//RelationShips
	private ExtraAttribute	extraAttribute;
	private Property		property;


	@Valid
	@ManyToOne(optional = false)
	public ExtraAttribute getExtraAttribute() {
		return extraAttribute;
	}

	public void setExtraAttribute(ExtraAttribute extraAttribute) {
		this.extraAttribute = extraAttribute;
	}

	@Valid
	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
