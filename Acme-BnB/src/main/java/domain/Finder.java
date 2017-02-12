
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	//Attributes
	private String	destination;
	private double	minimun;
	private double	maximum;
	private String	keyWord;


	//Constructor
	public Finder() {
		super();
	}

	//Getter & setter

	@NotBlank
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getMinimun() {
		return minimun;
	}

	public void setMinimun(double minimun) {
		this.minimun = minimun;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}


	//RelationShips
	private Tenant					tenant;
	private Collection<Property>	properties;


	@Valid
	@OneToOne(optional = false)
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	@ManyToMany()
	@JoinTable(name = "Finder_property", joinColumns = @JoinColumn(name = "finder_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"))
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

}
