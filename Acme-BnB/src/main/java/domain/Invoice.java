
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity {

	//Attributes
	private Date		moment;
	private double		vatNumber;
	private String		details;
	private String		information;
	private double		totalAmount;
	private CreditCard	creditCard;


	//Constructor
	public Invoice() {
		super();
	}
	//Getter & setter

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public double getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(double vatNumber) {
		this.vatNumber = vatNumber;
	}

	@NotBlank
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@NotBlank
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	//RelationShips

	private Book	book;


	@Valid
	@OneToOne(optional = false)
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
