
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Tenant extends Actor {

	//Attributes

	//Constructor
	public Tenant() {
		super();
	}


	//Getter & setter

	//RelationShips
	private Collection<Comment>	commentsAuthor;
	private Collection<Comment>	commentsRecipient;
	private Finder				finder;
	private Collection<Book>	books;


	@Valid
	@OneToMany(mappedBy = "tenantAuthor", cascade = CascadeType.ALL)
	public Collection<Comment> getCommentsAuthor() {
		return commentsAuthor;
	}

	public void setCommentsAuthor(Collection<Comment> commentsAuthor) {
		this.commentsAuthor = commentsAuthor;
	}

	@Valid
	@OneToMany(mappedBy = "tenantRecipient", cascade = CascadeType.ALL)
	public Collection<Comment> getCommentsRecipient() {
		return commentsRecipient;
	}

	public void setCommentsRecipient(Collection<Comment> commentsRecipient) {
		this.commentsRecipient = commentsRecipient;
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
