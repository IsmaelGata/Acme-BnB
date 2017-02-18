
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	//Attributes
	private String	destination;
	private double	minimun;
	private double	maximum;
	private String	keyWord;
	private Date	moment;


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

	@Min(0)
	public double getMinimun() {
		return minimun;
	}

	public void setMinimun(double minimun) {
		this.minimun = minimun;
	}

	@Min(1)
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

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}


	//RelationShips
	private Collection<Property>	listProperty;


	@Valid
	@ManyToMany(mappedBy = "finders")
	public Collection<Property> getListProperty() {
		return listProperty;
	}

	public void setListProperty(Collection<Property> listProperty) {
		this.listProperty = listProperty;
	}

}
