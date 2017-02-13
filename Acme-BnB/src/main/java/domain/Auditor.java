
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Auditor extends Actor {

	//Attributes

	private String	companyName;


	//Constructor
	public Auditor() {
		super();
	}

	//Getter & Setter

	@NotBlank
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	//RelationShips

	private Collection<Audit>	audits;


	@OneToMany(mappedBy = "auditor")
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}
}
