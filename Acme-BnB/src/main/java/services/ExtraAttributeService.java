
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExtraAttributeRepository;
import domain.ExtraAttribute;
import domain.RelatedValue;

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

	public ExtraAttribute create() {
		administratorService.findByPrincipal();
		ExtraAttribute result = new ExtraAttribute();
		Collection<RelatedValue> relatedValues = new ArrayList<>();

		result.setRelatedValues(relatedValues);
		return result;
	}

	public Collection<ExtraAttribute> findAll() {
		return extraAttributeRepository.findAll();
	}

	public ExtraAttribute findOne(int extraAttributeId) {
		return extraAttributeRepository.findOne(extraAttributeId);

	}

	public ExtraAttribute save(ExtraAttribute extraAttribute) {
		Assert.notNull(extraAttribute);
		ExtraAttribute result;
		administratorService.findByPrincipal();

		result = extraAttributeRepository.save(extraAttribute);

		return result;
	}

	public void delete(ExtraAttribute extraAttribute) {
		Assert.notNull(extraAttribute);
		administratorService.findByPrincipal();

		Assert.isTrue(!extraAttribute.getName().equals("City"));
		extraAttributeRepository.delete(extraAttribute);
	}

	public Collection<ExtraAttribute> concatExtraAttributes(Collection<RelatedValue> relatedValues) {
		Collection<ExtraAttribute> result = findAll();
		Collection<ExtraAttribute> aux = new ArrayList<>();
		for (RelatedValue relatedValue : relatedValues) {
			aux.add(relatedValue.getExtraAttribute());
		}

		result.removeAll(aux);

		return result;
	}

	//Other business methods

	/***
	 * Obtiene aquellos extraAttribute que no son los predefinidos por defecto
	 * 
	 * @return demás extra attributes
	 */
	public Collection<ExtraAttribute> getOtherExtraAttributes() {
		return extraAttributeRepository.otherExtraAttributes();
	}

	//DashBoard

	public Collection<ExtraAttribute> extraAttributesOrderByUse() {
		return extraAttributeRepository.extraAttributesOrderByUse();
	}
}
