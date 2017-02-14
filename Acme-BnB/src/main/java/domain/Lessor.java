
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends Actor {

	//Attributes
	private CreditCard	creditCard;
	private double		totalFee;


	//Constructor
	public Lessor() {
		super();
	}

	//Getter & setter

	@NotNull
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

	private Fee						fee;
	private Collection<Property>	properties;
	private Collection<Comment>		commentsAuthor;
	private Collection<Comment>		commentsRecipient;


	@Valid
	@ManyToOne(optional = false)
	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	@Valid
	@OneToMany(mappedBy = "lessor", cascade = CascadeType.ALL)
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

	@Valid
	@OneToMany(mappedBy = "lessorAuthor")
	public Collection<Comment> getCommentsAuthor() {
		return commentsAuthor;
	}

	public void setCommentsAuthor(Collection<Comment> commentsAuthor) {
		this.commentsAuthor = commentsAuthor;
	}

	@Valid
	@OneToMany(mappedBy = "lessorRecipient")
	public Collection<Comment> getCommentsRecipient() {
		return commentsRecipient;
	}

	public void setCommentsRecipient(Collection<Comment> commentsRecipient) {
		this.commentsRecipient = commentsRecipient;
	}

}
