
package form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class CommentForm {

	//Attributes

	private int			id;
	private String		title;
	private String		text;
	private Integer		stars;
	private int			comentableId;
	private String		comentableType;


	//Constructor
	public CommentForm() {
		super();
	}

	//getter & setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
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
}
