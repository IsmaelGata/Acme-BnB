
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Embeddable
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {

	//Attributes
	private String	nick;
	private String	StringnameSocialNetwork;
	private String	URL;


	//Constructor
	public SocialIdentity() {
		super();
	}
	//Getter & setter

	@NotBlank
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@NotBlank
	public String getStringnameSocialNetwork() {
		return StringnameSocialNetwork;
	}

	public void setStringnameSocialNetwork(String stringnameSocialNetwork) {
		StringnameSocialNetwork = stringnameSocialNetwork;
	}

	@NotBlank
	@URL
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}


	//RelationShips
	private Actor	actor;


	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
