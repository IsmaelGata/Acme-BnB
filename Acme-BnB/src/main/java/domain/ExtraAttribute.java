
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = "name")
})
public class ExtraAttribute extends DomainEntity {

	//Attributes
	private String	name;
	private Type	type;


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

	@NotNull
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


	//RelationShips

	private Collection<RelatedValue>	relatedValues;


	@Valid
	@OneToMany(mappedBy = "extraAttribute", cascade = CascadeType.ALL)
	public Collection<RelatedValue> getRelatedValues() {
		return relatedValues;
	}

	public void setRelatedValues(Collection<RelatedValue> relatedValues) {
		this.relatedValues = relatedValues;
	}

}
