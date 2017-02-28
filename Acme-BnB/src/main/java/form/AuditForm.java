
package form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class AuditForm {

	//Attributes
	private int		id;
	private int		idProperty;
	private String	text;
	private String	attachments;
	private boolean	draft;


	//Constructor
	public AuditForm() {
		super();
	}
	//Getter & setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(int idProperty) {
		this.idProperty = idProperty;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

}
