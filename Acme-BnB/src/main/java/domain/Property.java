
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String	country;
	private String	province;
	private String	state;
	private String	city;
	private int		capability;


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

	@NotBlank
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@NotBlank
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Min(0)
	public int getCapability() {
		return capability;
	}

	public void setCapability(int capability) {
		this.capability = capability;
	}


	//RelationShips

	private Collection<Audit>			audits;
	private Lessor						lessor;
	private Collection<Book>			books;
	private Collection<ExtraAttribute>	extraAttributes;
	private Collection<Finder>			finders;


	@Valid
	@OneToMany(mappedBy = "property")
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
	@OneToMany(mappedBy = "property")
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	@Valid
	@ManyToMany(mappedBy = "properties")
	public Collection<ExtraAttribute> getExtraAttributes() {
		return extraAttributes;
	}

	public void setExtraAttributes(Collection<ExtraAttribute> extraAttributes) {
		this.extraAttributes = extraAttributes;
	}

	@Valid
	@ManyToMany
	@JoinTable(name = "finder_property", joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "finder_id", referencedColumnName = "id"))
	public Collection<Finder> getFinders() {
		return finders;
	}

	public void setFinders(Collection<Finder> finders) {
		this.finders = finders;
	}
}
