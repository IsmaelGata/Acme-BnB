
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
	private int		comentableId;
	private String	comentableType;


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

	@Min(1)
	public int getComentableId() {
		return comentableId;
	}

	public void setComentableId(int comentableId) {
		this.comentableId = comentableId;
	}

	@NotBlank
	public String getComentableType() {
		return comentableType;
	}

	public void setComentableType(String comentableType) {
		this.comentableType = comentableType;
	}


	//RelationShips
	private ComentatorActor	author;


	@Valid
	@NotNull
	@ManyToOne(optional = true)
	public ComentatorActor getAuthor() {
		return author;
	}

	public void setAuthor(ComentatorActor author) {
		this.author = author;
	}

}
