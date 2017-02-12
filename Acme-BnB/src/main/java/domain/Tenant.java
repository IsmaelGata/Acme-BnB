
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Embeddable
@Access(AccessType.PROPERTY)
public class Tenant extends Actor {

	//Attributes

	//Constructor
	public Tenant() {
		super();
	}


	//Getter & setter

	//RelationShips
	private Collection<Comment>	comments;
	private Finder				finder;
	private Collection<Book>	books;


	@Valid
	@OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public Finder getFinder() {
		return finder;
	}

	public void setFinder(Finder finder) {
		this.finder = finder;
	}

	@Valid
	@OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

}
