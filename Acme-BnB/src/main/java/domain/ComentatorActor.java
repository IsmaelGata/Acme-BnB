
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class ComentatorActor extends Actor {

	//Attributes

	//COnstructor

	//Getter & setter

	//RelationShips

	private Collection<Comment>	authoredComments;


	@Valid
	@OneToMany(mappedBy = "author")
	public Collection<Comment> getAuthoredComments() {
		return authoredComments;
	}

	public void setAuthoredComments(Collection<Comment> authoredComments) {
		this.authoredComments = authoredComments;
	}

}
