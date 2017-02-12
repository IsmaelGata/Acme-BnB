
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	//Attributes

	//Constructor
	public Administrator() {
		super();
	}

	//Getter & Setter

	//RelationShips

}
