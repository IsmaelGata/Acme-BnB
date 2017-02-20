
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExtraAttributeRepository;
import domain.ExtraAttribute;
import domain.Property;

@Service
@Transactional
public class ExtraAttributeService {

	//Managed repository

	@Autowired
	private ExtraAttributeRepository	extraAttributeRepository;

	//Supported services

	@Autowired
	private AdministratorService		administratorService;


	//Constructor

	public ExtraAttributeService() {
		super();
	}

	//Simple CRUD methods

	public ExtraAttribute create(Property property) {
		administratorService.findByPrincipal();
		Assert.notNull(property);
		ExtraAttribute result = new ExtraAttribute();
		Collection<Property> properties = new ArrayList<>();

		result.setProperties(properties);
		return result;
	}

	public Collection<ExtraAttribute> findAll() {
		return extraAttributeRepository.findAll();
	}

	public ExtraAttribute findOne(int extraAttributeId) {
		return extraAttributeRepository.findOne(extraAttributeId);

	}

	public void save(ExtraAttribute extraAttribute) {
		Assert.notNull(extraAttribute);
		administratorService.findByPrincipal();
		
		extraAttributeRepository.save(extraAttribute);
	}

	public void delete(ExtraAttribute extraAttribute) {
		Assert.notNull(extraAttribute);
		administratorService.findByPrincipal();
		
		extraAttributeRepository.delete(extraAttribute);
	}

	//Other business methods

}
