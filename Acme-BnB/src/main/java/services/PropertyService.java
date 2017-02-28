
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

import domain.Book;
import domain.ExtraAttribute;
import domain.Lessor;
import domain.Property;
import domain.RelatedValue;
import form.PropertyForm;
import repositories.PropertyRepository;

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
		Collection<ExtraAttribute> defaultExtraAttributes = getDefaultExtraAttributes();
		Collection<ExtraAttribute> defaultExtraAttributesCopy = getDefaultExtraAttributes();
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
		
		for(Object[] o: propertyOrderAudits){
			String name = o[0].toString();
			if(!mapPropertyOderAudits.containsKey(name)){
				mapPropertyOderAudits.put(name, new ArrayList<Property>());
			}
			Property property = (Property)o[1];
			mapPropertyOderAudits.get(name).add(property);
		}
		
		return mapPropertyOderAudits;
	}

	public  Map<String, Collection<Property>> getPropertyOrderBook() {
		Collection<Object[]> propertyOrderBook = propertyRepository.getPropertyOrderBook();
		Map<String, Collection<Property>> mapPropertyOrderBook = new HashMap<String, Collection<Property>>();
		
		for(Object[] o: propertyOrderBook){
			String name = o[0].toString();
			if(!mapPropertyOrderBook.containsKey(name)){
				mapPropertyOrderBook.put(name, new ArrayList<Property>());
			}
			Property property = (Property)o[1];
			mapPropertyOrderBook.get(name).add(property);
		}
		
		return mapPropertyOrderBook;
	}

	public Map<String, Map<Integer, Collection<Property>>> getPropertyOrderBookAcepted() {
		Collection<Object[]> propertyOrderBookAcepted = propertyRepository.getPropertyOrderBook();
		Map<String, Map<Integer, Collection<Property>>> mapPropertyOrderBookAcepted = new HashMap<String, Map<Integer, Collection<Property>>>();
		
		for(Object[] o: propertyOrderBookAcepted){
			String name = o[0].toString();
			if(!mapPropertyOrderBookAcepted.containsKey(name)){
				mapPropertyOrderBookAcepted.put(name, new HashMap<Integer, Collection<Property>>());
			}
			Property property = (Property)o[1];
			Integer count = (Integer) o[2];
			if(!mapPropertyOrderBookAcepted.get(name).containsKey(count)){
				mapPropertyOrderBookAcepted.get(name).put(count, new ArrayList<Property>());
			}
			mapPropertyOrderBookAcepted.get(name).get(count).add(property);
		}
		
		return mapPropertyOrderBookAcepted;
	}

	public Collection<Object[]> getPropertyOrderBookDenied(int lessorId) {
		return propertyRepository.getPropertyOrderBookDenied(lessorId);
	}

	public Collection<Object[]> getPropertyOrderBookPending(int lessorId) {
		return propertyRepository.getPropertyOrderBookPending(lessorId);
	}
}
