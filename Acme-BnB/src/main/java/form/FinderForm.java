
package form;

import java.util.Collection;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Property;

public class FinderForm {

	//Attributes
	private String					destination;
	private Double					minimun;
	private Double					maximum;
	private String					keyWord;
	private Collection<Property>	listProperties;


	//Constructor
	public FinderForm() {
		super();
	}
	//Getter & setter

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Min(0)
	public Double getMinimun() {
		return minimun;
	}

	public void setMinimun(Double minimun) {
		this.minimun = minimun;
	}

	@Min(1)
	public Double getMaximum() {
		return maximum;
	}

	public void setMaximum(Double maximum) {
		this.maximum = maximum;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Collection<Property> getListProperties() {
		return listProperties;
	}

	public void setListProperties(Collection<Property> listProperties) {
		this.listProperties = listProperties;
	}

}
