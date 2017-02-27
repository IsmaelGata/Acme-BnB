
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PropertyRepository;
import domain.Book;
import domain.ExtraAttribute;
import domain.Lessor;
import domain.Property;
import domain.RelatedValue;
import form.PropertyForm;

@Service
@Transactional
public class PropertyService {

	//Managed repository

	@Autowired
	private PropertyRepository		propertyRepository;

	//Supported services
	@Autowired
	private LessorService			lessorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private RelatedValueService		relatedValueService;


	//Constructor

	public PropertyService() {
		super();
	}

	//Simple CRUD methods

	public PropertyForm create() {
		lessorService.findByPrincipal();

		Collection<ExtraAttribute> extraAttributes = getDefaultExtraAttributes();
		ArrayList<RelatedValue> relatedValues = new ArrayList<>();
		PropertyForm result = new PropertyForm();

		for (ExtraAttribute extraAttribute : extraAttributes) {
			RelatedValue relatedValue = relatedValueService.create();
			relatedValue.setExtraAttribute(extraAttribute);
			relatedValues.add(relatedValue);
		}
		result.setExtraAttributes(extraAttributes);
		result.setRelatedValues(relatedValues);
		result.setId(0);
		
		return result;
	}
	public Collection<Property> findAll() {
		return propertyRepository.findAll();
	}

	public Property findOne(int propertyId) {
		return propertyRepository.findOne(propertyId);

	}

	public Property save(Property property) {
		Assert.notNull(property);
		Lessor lessor = lessorService.findByPrincipal();

		Assert.isTrue(lessor.equals(property.getLessor()));
		Collection<RelatedValue> relatedValues = property.getRelatedValues();
		property.setRelatedValues(null);
		Property result = propertyRepository.save(property);

		if (relatedValues != null) {
			relatedValues = relatedValueService.assignProperty(relatedValues, result);
			relatedValueService.saveAll(relatedValues);
		}

		return result;
	}

	public void delete(Property property) {
		Assert.notNull(property);
		Assert.isTrue(property.getBooks().isEmpty());
		Lessor lessor = lessorService.findByPrincipal();
		Assert.isTrue(lessor.equals(property.getLessor()));
		propertyRepository.delete(property);
	}


	//Other business methods

	@Autowired
	private Validator	validator;


	public Property reconstruct(PropertyForm propertyForm, BindingResult binding) {
		Assert.notNull(propertyForm);
		Property result = new Property();
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		//Checking property's existence
		if (propertyForm.getId() != 0) {
			result.setId(propertyForm.getId());
			Property backed = findOne(propertyForm.getId());
			result.setAudits(backed.getAudits());
			result.setBooks(backed.getBooks());
			result.setRelatedValues(backed.getRelatedValues());
		} else {
			result.setAddress(propertyForm.getAddress());
			result.setLessor(lessor);
			result.setBooks(new ArrayList<Book>());
		}

		result.setName(propertyForm.getName());
		result.setDescription(propertyForm.getDescription());
		result.setRate(propertyForm.getRate());
		result.setRelatedValues(propertyForm.getRelatedValues());
		//**********************************************

		return result;
	}
	@SuppressWarnings("unchecked")
	public PropertyForm conversionToFormObject(Property property) {
		Assert.notNull(property);

		PropertyForm result = new PropertyForm();

		result.setId(property.getId());
		result.setAddress(property.getAddress());
		result.setDescription(property.getDescription());
		result.setName(property.getName());
		result.setRate(property.getRate());
		result.setRelatedValues((ArrayList) property.getRelatedValues());
		Collection<ExtraAttribute> extraAttributes = new ArrayList<>();
		for (RelatedValue relatedValue : property.getRelatedValues()) {
			extraAttributes.add(relatedValue.getExtraAttribute());
		}
		result.setExtraAttributes(extraAttributes);

		return result;
	}

	public Collection<ExtraAttribute> getDefaultExtraAttributes() {
		return propertyRepository.getDefaultExtraAttributes();
	}

	//Dashboard

	public Double getAverageAuditsPerProperty() {
		administratorService.findByPrincipal();

		return propertyRepository.getAverageAuditsPerProperty();
	}

	public Integer getMinimumAuditsPerProperty() {
		administratorService.findByPrincipal();

		return propertyRepository.getMinimumAuditsPerProperty();
	}

	public Integer getMaximumAuditsPerProperty() {
		administratorService.findByPrincipal();

		return propertyRepository.getMaximumAuditsPerProperty();
	}

	public Collection<Property> getPropertyOrderAudits(int lessorId) {
		return propertyRepository.getPropertyOrderAudits(lessorId);
	}

	public Collection<Property> getPropertyOrderBook(int lessorId) {
		return propertyRepository.getPropertyOrderBook(lessorId);
	}

	public Collection<Object[]> getPropertyOrderBookAcepted(int lessorId) {
		return propertyRepository.getPropertyOrderBookAcepted(lessorId);
	}

	public Collection<Object[]> getPropertyOrderBookDenied(int lessorId) {
		return propertyRepository.getPropertyOrderBookDenied(lessorId);
	}

	public Collection<Object[]> getPropertyOrderBookPending(int lessorId) {
		return propertyRepository.getPropertyOrderBookPending(lessorId);
	}
}
