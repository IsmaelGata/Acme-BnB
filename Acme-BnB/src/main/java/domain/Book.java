
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Book extends DomainEntity {

	//Attributes
	private Date		checkIn;
	private Date		checkOut;
	private boolean		smoker;
	private CreditCard	creditCard;
	private Status		status;


	//Constructor
	public Book() {
		super();
	}

	//Getter & setter

	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	//RelationShips

	private Invoice		invoice;
	private Property	property;
	private Tenant		tenant;


	@Valid
	@OneToOne(optional = true)
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Valid
	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Valid
	@ManyToOne(optional = false)
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
