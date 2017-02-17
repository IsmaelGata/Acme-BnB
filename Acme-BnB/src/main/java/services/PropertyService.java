
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import domain.Lessor;
import domain.Property;
import form.PropertyForm;

@Service
@Transactional
public class PropertyService {

	//Managed repository

	@Autowired
	private PropertyRepository	propertyRepository;

	//Supported services
	@Autowired
	private LessorService		lessorService;


	//Constructor

	public PropertyService() {
		super();
	}

	//Simple CRUD methods

	public PropertyForm create() {
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		PropertyForm result = new PropertyForm();

		return result;
	}

	public Collection<Property> findAll() {
		return propertyRepository.findAll();
	}

	public Property findOne(int id_property) {
		return propertyRepository.findOne(id_property);

	}

	public Property save(PropertyForm propertyForm) {
		Assert.notNull(propertyForm);
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		Property property = reconstruct(propertyForm);
		Assert.isTrue(lessor.equals(property.getLessor()));

		Property result = propertyRepository.save(property);

		return result;
	}

	public void delete(Property property) {
		Assert.notNull(property);
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);
		Assert.isTrue(lessor.equals(property.getLessor()));

		propertyRepository.delete(property);
	}

	//Other business methods

	public Property reconstruct(PropertyForm propertyForm) {
		Assert.notNull(propertyForm);
		Property result = new Property();
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		result.setAddress(propertyForm.getAddress());
		result.setLessor(lessor);
		result.setCapability(propertyForm.getCapability());
		result.setCity(propertyForm.getCity());
		result.setCountry(propertyForm.getCountry());
		result.setDescription(propertyForm.getDescription());
		result.setName(propertyForm.getName());
		result.setProvince(propertyForm.getProvince());
		result.setRate(propertyForm.getRate());
		result.setState(propertyForm.getState());

		if (propertyForm.getId() != 0) {
			result.setId(propertyForm.getId());
			Property backed = findOne(propertyForm.getId());
			result.setAudits(backed.getAudits());
			result.setBooks(backed.getBooks());
			result.setExtraAttributes(backed.getExtraAttributes());
		}

		return result;
	}

	public PropertyForm convertionToFormObject(Property property) {
		Assert.notNull(property);

		PropertyForm result = new PropertyForm();

		result.setId(property.getId());
		result.setAddress(property.getAddress());
		result.setCapability(property.getCapability());
		result.setCity(property.getCity());
		result.setCountry(property.getCountry());
		result.setDescription(property.getDescription());
		result.setName(property.getName());
		result.setProvince(property.getProvince());
		result.setRate(property.getRate());
		result.setState(property.getState());

		return result;
	}
}
