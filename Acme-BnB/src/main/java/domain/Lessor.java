
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

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
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}


	//RelationShips

	private Collection<Property>	listProperty;


	@Valid
	@OneToMany(mappedBy = "lessor", cascade = CascadeType.ALL)
	public Collection<Property> getListProperty() {
		return listProperty;
	}

	public void setListProperty(Collection<Property> listProperty) {
		this.listProperty = listProperty;
	}

}
