
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import domain.Lessor;
import domain.Property;

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

	public Property create() {
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		Property result = new Property();
		result.setLessor(lessor);

		return result;
	}

	public Collection<Property> findAll() {
		return propertyRepository.findAll();
	}

	public Property findOne(int id_property) {
		return propertyRepository.findOne(id_property);

	}

	public Property save(Property property) {
		Assert.notNull(property);
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);
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

}
