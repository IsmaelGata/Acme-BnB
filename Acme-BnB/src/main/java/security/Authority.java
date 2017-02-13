/* Authority.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

	// Constructors -----------------------------------------------------------

	private static final long serialVersionUID = 1L;

	public Authority() {
		super();
	}

	// Values -----------------------------------------------------------------

	public static final String ADMINISTRATOR = "ADMINISTRATOR";
	public static final String AUDITOR = "AUDITOR";
	public static final String LESSOR = "LESSOR";
	public static final String TENANT = "TENANT";
	

	// Attributes -------------------------------------------------------------

	private String authority;

	@NotBlank
	@Pattern(regexp = "^" + ADMINISTRATOR + "|" + AUDITOR + "|" + LESSOR + "|" + TENANT + "$")
	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public static Collection<Authority> listAuthorities() {
		Collection<Authority> result;
		Authority authority;

		result = new ArrayList<Authority>();

		authority = new Authority();
		authority.setAuthority(ADMINISTRATOR);
		result.add(authority);

		authority = new Authority();
		authority.setAuthority(AUDITOR);
		result.add(authority);
		
		authority = new Authority();
		authority.setAuthority(LESSOR);
		result.add(authority);
		
		authority = new Authority();
		authority.setAuthority(TENANT);
		result.add(authority);

		return result;
	}

	// Equality ---------------------------------------------------------------

	@Override
	public int hashCode() {
		return this.getAuthority().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		boolean result;

		if (this == other)
			result = true;
		else if (other == null)
			result = false;
		else if (!this.getClass().isInstance(other))
			result = false;
		else
			result = (this.getAuthority().equals(((Authority) other).getAuthority()));

		return result;
	}

}
