
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RelatedValueRepository;
import domain.Property;
import domain.RelatedValue;

@Service
@Transactional
public class RelatedValueService {

	//Managed repository

	@Autowired
	private RelatedValueRepository	relatedValueRepository;


	//Supported services

	//Constructor

	public RelatedValueService() {
		super();
	}

	//Simple CRUD methods

	public Collection<RelatedValue> findAll() {
		return relatedValueRepository.findAll();
	}

	public RelatedValue findOne(int id) {
		return relatedValueRepository.findOne(id);

	}

	public RelatedValue create() {
		RelatedValue result = new RelatedValue();

		return result;
	}

	public void save(RelatedValue relatedValue) {
		Assert.notNull(relatedValue);

		relatedValueRepository.save(relatedValue);
	}

	public void saveAll(Collection<RelatedValue> relatedValues) {
		Assert.notNull(relatedValues);

		relatedValueRepository.save(relatedValues);
	}

	public void delete(RelatedValue relatedValue) {
		Assert.notNull(relatedValue);

		relatedValueRepository.delete(relatedValue);
	}

	public void deleteAll(Collection<RelatedValue> relatedValues) {
		Assert.notNull(relatedValues);

		relatedValueRepository.delete(relatedValues);
	}

	//Other Business services

	public void assignProperty(Collection<RelatedValue> relatedValues, Property property) {
		Assert.notNull(relatedValues);
		for (RelatedValue relatedValue : relatedValues) {
			relatedValue.setProperty(property);
		}
	}
}
