package form;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

public class TenantForm {

	
	public TenantForm(){
		super();
	}
	
	private String username;
	private String password;
	private String repeatPassword;
	private Boolean acceptCondition;
	private String	name;
	private String	surname;
	private String	phone;
	private String	email;
	private String	picture;
	
	@Size(min = 5, max = 32)
	@Column(unique = true)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	@AssertTrue
	public Boolean getAcceptCondition() {
		return acceptCondition;
	}
	public void setAcceptCondition(Boolean acceptCondition) {
		this.acceptCondition = acceptCondition;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@NotBlank
	@Pattern(regexp = "(\\+|00)\\d{2,4}(\\s)?\\d{9,13}")
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@NotBlank
	@URL
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
