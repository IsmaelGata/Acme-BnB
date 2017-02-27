
package form;

import java.util.ArrayList;
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

	private Integer							id;
	private String								name;
	private Double							rate;
	private String								description;
	private String								address;
	private Collection<ExtraAttribute>	extraAttributes;
	private ArrayList<RelatedValue>	relatedValues;


	//Constructor

	public PropertyForm() {
		super();
	}

	//Getter & setter

	 @NotNull
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	@NotNull
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
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

	public Collection<ExtraAttribute> getExtraAttributes() {
		return extraAttributes;
	}

	public void setExtraAttributes(Collection<ExtraAttribute> extraAttributes) {
		this.extraAttributes = extraAttributes;
	}

	public Collection<RelatedValue> getRelatedValues() {
		return relatedValues;
	}

	public void setRelatedValues(ArrayList<RelatedValue> relatedValues) {
		this.relatedValues = relatedValues;
	}

}
