
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	//Attributes
	private String	title;
	private Date	moment;
	private String	text;
	private int		stars;


	//Constructor
	public Comment() {
		super();
	}

	//getter & setter

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Min(0)
	@Max(5)
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}


	//RelationShips
	private Lessor	lessor;
	private Tenant	tenant;


	@Valid
	@ManyToOne(optional = true)
	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
	}

	@Valid
	@ManyToOne(optional = true)
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
