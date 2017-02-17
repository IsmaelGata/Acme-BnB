
package form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class SocialIdentityForm {

	//Attributes
	private int		id;
	private String	nick;
	private String	nameSocialNetwork;
	private String	URL;


	//Constructor
	public SocialIdentityForm() {
		super();
	}
	//Getter & setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@NotBlank
	public String getNameSocialNetwork() {
		return nameSocialNetwork;
	}

	public void setNameSocialNetwork(String nameSocialNetwork) {
		this.nameSocialNetwork = nameSocialNetwork;
	}

	@NotBlank
	@URL
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
}
