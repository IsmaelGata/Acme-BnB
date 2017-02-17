
package form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class PropertyForm {

	//Attributes

	private int		id;
	private String	name;
	private double	rate;
	private String	description;
	private String	address;
	private String	country;
	private String	province;
	private String	state;
	private String	city;
	private int		capability;


	//Constructor

	public PropertyForm() {
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Min(0)
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotBlank
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@NotBlank
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Min(0)
	public int getCapability() {
		return capability;
	}

	public void setCapability(int capability) {
		this.capability = capability;
	}
}
