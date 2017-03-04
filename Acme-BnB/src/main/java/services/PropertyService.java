
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	@Autowired
	private ExtraAttributeService	extraAttributeService;


	//Constructor

	public PropertyService() {
		super();
	}

	//Simple CRUD methods

	public PropertyForm create() {
		lessorService.findByPrincipal();

		Collection<ExtraAttribute> extraAttributes = extraAttributeService.findAll();
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
			if (result.getLessor() == null) {
				Assert.isTrue(lessor.equals(backed.getLessor()));
				result.setLessor(lessor);
			}
		} else {
			result.setLessor(lessor);
			result.setBooks(new ArrayList<Book>());
		}

		result.setAddress(propertyForm.getAddress());
		result.setName(propertyForm.getName());
		result.setDescription(propertyForm.getDescription());
		result.setRate(propertyForm.getRate());
		ArrayList<RelatedValue> aux = new ArrayList<>();
		for (RelatedValue relatedValue : propertyForm.getRelatedValues()) {
			if (!relatedValue.getValue().isEmpty()) {
				validator.validate(relatedValue, binding);
				aux.add(relatedValue);
			}
		}
		propertyForm.setRelatedValues(aux);
		if (!binding.hasErrors()) {
			result.setRelatedValues(propertyForm.getRelatedValues());
		}

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
		result.setRelatedValues(new ArrayList<>(property.getRelatedValues()));
		//		result.setRelatedValues((ArrayList) property.getRelatedValues());
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

	public PropertyForm assingDefaultRelatedValues(PropertyForm propertyForm) {
		Collection<ExtraAttribute> defaultExtraAttributes = extraAttributeService.findAll();
		Collection<ExtraAttribute> defaultExtraAttributesCopy = extraAttributeService.findAll();
		Collection<ExtraAttribute> propertyExtraAttributes = propertyForm.getExtraAttributes();
		ArrayList<RelatedValue> relatedValues = new ArrayList<RelatedValue>(propertyForm.getRelatedValues());

		defaultExtraAttributes.retainAll(propertyExtraAttributes);
		defaultExtraAttributesCopy.removeAll(defaultExtraAttributes);
		if (defaultExtraAttributesCopy.size() > 0) {
			for (ExtraAttribute extraAttribute : defaultExtraAttributesCopy) {
				RelatedValue relatedValue = relatedValueService.create();
				relatedValue.setExtraAttribute(extraAttribute);
				relatedValues.add(relatedValue);
			}
			propertyForm.setRelatedValues(relatedValues);
		}

		return propertyForm;
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

	public Map<String, Collection<Property>> getPropertyOrderAudits() {
		Collection<Object[]> propertyOrderAudits = propertyRepository.getPropertyOrderAudits();
		Map<String, Collection<Property>> mapPropertyOderAudits = new HashMap<String, Collection<Property>>();

		for (Object[] o : propertyOrderAudits) {
			String name = o[0].toString();
			if (!mapPropertyOderAudits.containsKey(name)) {
				mapPropertyOderAudits.put(name, new ArrayList<Property>());
			}
			Property property = (Property) o[1];
			mapPropertyOderAudits.get(name).add(property);
		}

		return mapPropertyOderAudits;
	}

	public Map<String, Collection<Property>> getPropertyOrderBook() {
		Collection<Object[]> propertyOrderBook = propertyRepository.getPropertyOrderBook();
		Map<String, Collection<Property>> mapPropertyOrderBook = new HashMap<String, Collection<Property>>();

		for (Object[] o : propertyOrderBook) {
			String name = o[0].toString();
			if (!mapPropertyOrderBook.containsKey(name)) {
				mapPropertyOrderBook.put(name, new ArrayList<Property>());
			}
			Property property = (Property) o[1];
			mapPropertyOrderBook.get(name).add(property);
		}

		return mapPropertyOrderBook;
	}

	public Map<String, Collection<Property>> getPropertyOrderBookAcepted() {
		Collection<Object[]> propertyOrderBookAcepted = propertyRepository.getPropertyOrderBookAcepted();
		Map<String, Collection<Property>> mapPropertyOrderBookAcepted = new HashMap<String, Collection<Property>>();

		for (Object[] o : propertyOrderBookAcepted) {
			String name = o[0].toString();
			if (!mapPropertyOrderBookAcepted.containsKey(name)) {
				mapPropertyOrderBookAcepted.put(name, new ArrayList<Property>());
			}
			Property property = (Property) o[1];
			mapPropertyOrderBookAcepted.get(name).add(property);
		}

		return mapPropertyOrderBookAcepted;
	}

	public Map<String, Collection<Property>> getPropertyOrderBookDenied() {
		Collection<Object[]> propertyOrderBookDenied = propertyRepository.getPropertyOrderBookDenied();
		Map<String, Collection<Property>> mapPropertyOrderBookDenied = new HashMap<String, Collection<Property>>();

		for (Object[] o : propertyOrderBookDenied) {
			String name = o[0].toString();
			if (!mapPropertyOrderBookDenied.containsKey(name)) {
				mapPropertyOrderBookDenied.put(name, new ArrayList<Property>());
			}
			Property property = (Property) o[1];
			mapPropertyOrderBookDenied.get(name).add(property);
		}

		return mapPropertyOrderBookDenied;
	}

	public Map<String, Collection<Property>> getPropertyOrderBookPending() {
		Collection<Object[]> propertyOrderBookPending = propertyRepository.getPropertyOrderBookPending();
		Map<String, Collection<Property>> mapPropertyOrderBookPending = new HashMap<String, Collection<Property>>();

		for (Object[] o : propertyOrderBookPending) {
			String name = o[0].toString();
			if (!mapPropertyOrderBookPending.containsKey(name)) {
				mapPropertyOrderBookPending.put(name, new ArrayList<Property>());
			}
			Property property = (Property) o[1];
			mapPropertyOrderBookPending.get(name).add(property);
		}

		return mapPropertyOrderBookPending;
	}

}
