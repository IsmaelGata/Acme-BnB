
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity {

	//Attributes
	private String	name;
	private double	rate;
	private String	description;
	private String	address;


	//Constructor

	public Property() {
		super();
	}

	//Getter & setter

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Min(0)
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	//RelationShips

	private Collection<Audit>			audits;
	private Lessor						lessor;
	private Collection<Book>			books;
	private Collection<RelatedValue>	relatedValues;


	@Valid
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

	@Valid
	@ManyToOne(optional = false)
	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
	}

	@Valid
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "property")
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	@Valid
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	public Collection<RelatedValue> getRelatedValues() {
		return relatedValues;
	}

	public void setRelatedValues(Collection<RelatedValue> relatedValues) {
		this.relatedValues = relatedValues;
	}

}
