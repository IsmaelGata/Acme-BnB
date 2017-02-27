
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.joda.time.Days;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends ComentatorActor {

	//Attributes
	private CreditCard	creditCard;
	private double		totalFee;


	//Constructor
	public Lessor() {
		super();
	}

	//Getter & setter

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Transient
	public double getTotalFee() {
		double total = 0.;
		final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
		for(Property p: this.getProperties()){
			for(Book b: p.getBooks()){
				if(b.getStatus() == Status.PENDING){
					int diffInDays = (int) ((b.getCheckIn().getTime() - b.getCheckOut().getTime())/ DAY_IN_MILLIS );
					total += p.getRate()*Math.abs(diffInDays);
				}
			}
		}
		totalFee = total;
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}


	//RelationShips

	private Collection<Property>	properties;


	@Valid
	@OneToMany(mappedBy = "lessor", cascade = CascadeType.ALL)
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

}
