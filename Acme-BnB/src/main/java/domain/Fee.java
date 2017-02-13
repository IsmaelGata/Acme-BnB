
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	//Attributes
	private double	amount;


	//Constructor

	public Fee() {
		super();
	}

	//Getter & setter

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	//RelationShips
}
