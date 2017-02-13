
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
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
	private Tenant	tenant;

	@Valid
	@OneToOne(optional = false)
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
