
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Tenant extends ComentatorActor {

	//Attributes

	//Constructor
	public Tenant() {
		super();
	}


	//Getter & setter

	//RelationShips
	private Collection<Book>	books;


	@Valid
	@OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

}
