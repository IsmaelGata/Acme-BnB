
package form;

import java.util.Collection;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.ExtraAttribute;
import domain.RelatedValue;

public class PropertyForm {

	//Attributes

	private int							id;
	private String						name;
	private double						rate;
	private String						description;
	private String						address;
	private Collection<ExtraAttribute>	extraAttributes;
	private Collection<RelatedValue>	relatedValues;


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
	@SafeHtml(whitelistType = WhiteListType.NONE)
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
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull
	public Collection<ExtraAttribute> getExtraAttributes() {
		return extraAttributes;
	}

	public void setExtraAttributes(Collection<ExtraAttribute> extraAttributes) {
		this.extraAttributes = extraAttributes;
	}

	@NotNull
	public Collection<RelatedValue> getRelatedValues() {
		return relatedValues;
	}

	public void setRelatedValues(Collection<RelatedValue> relatedValues) {
		this.relatedValues = relatedValues;
	}

}
