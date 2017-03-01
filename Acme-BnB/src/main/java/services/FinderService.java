
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.FinderRepository;
import domain.Finder;
import domain.Property;
import form.FinderForm;

@Service
@Transactional
public class FinderService {

	// Managed repository
	@Autowired
	private FinderRepository		finderRepository;

	// Supported services

	@Autowired
	private TenantService			tenantService;

	@Autowired
	private AdministratorService	administratorService;


	// Constructor

	public FinderService() {
		super();
	}

	// Simple CRUD methods

	public Finder create() {
		Finder result = new Finder();
		return result;
	}

	public Collection<Finder> findAll() {
		return finderRepository.findAll();
	}

	public Finder findOne(int finderId) {
		return finderRepository.findOne(finderId);

	}

	public void save(Finder finder) {
		Assert.notNull(finder);
		tenantService.findByPrincipal();
		finder.setMoment(new Date());
		finderRepository.save(finder);
	}

	public void delete(Finder finder) {
		Assert.notNull(finder);

		finderRepository.delete(finder);
	}

	// Other business methods

	public Finder reconstruct(FinderForm finderForm, BindingResult binding) {
		Finder result = new Finder();

		result.setDestination(finderForm.getDestination());
		result.setKeyWord(finderForm.getKeyWord());
		result.setMaximum(finderForm.getMaximum());
		result.setMinimun(finderForm.getMinimun());
		result.setMoment(new Date(System.currentTimeMillis() - 10));

		return result;
	}

	public Collection<Property> findProperties(String city, Double min, Double max, String keyWord) {
		return finderRepository.findProperty(city, min, max, keyWord);
	}

	private Collection<Property> findProperties(String city, Double min, Double max) {
		return finderRepository.findProperty(city, min, max);
	}

	private Collection<Property> findProperties(String city, String keyWord) {
		return finderRepository.findProperty(city, keyWord);
	}

	private Collection<Property> findProperties(String city) {
		return finderRepository.findProperty(city);
	}

	public Finder getSavedFinder(String city, Double min, Double max, String keyWord) {
		Finder result;
		if (min == null && max == null) {
			result = finderRepository.getSavedFinder(city, 0.0, 99999999999999.0, keyWord);
		} else if (min == null && max != null) {
			result = finderRepository.getSavedFinder(city, 0.0, max, keyWord);
		} else if (min != null && max == null) {
			result = finderRepository.getSavedFinder(city, min, 99999999999999.0, keyWord);
		} else {
			result = finderRepository.getSavedFinder(city, min, max, keyWord);
		}
		return result;
	}

	public Finder finderEngine(String city, Double min, Double max, String keyWord) {
		Finder result = new Finder();
		result.setDestination(city);

		Finder savedFinder = getSavedFinder(city, min, max, keyWord);

		if (savedFinder == null) {
			if (min != null && max != null && keyWord != null) {
				result.setListProperty(findProperties(city, min, max, keyWord));
				result.setKeyWord(keyWord);
				result.setMaximum(max);
				result.setMinimun(min);
			} else if (min != null && max != null && keyWord.isEmpty()) {
				result.setListProperty(findProperties(city, min, max));
				result.setKeyWord("");
				result.setMaximum(max);
				result.setMinimun(min);
			} else if (min == null && max == null && (!keyWord.isEmpty())) {
				result.setListProperty(findProperties(city, keyWord));
				result.setKeyWord(keyWord);
				result.setMaximum(99999999999999.0);
				result.setMinimun(0.0);
			} else if (min == null && max != null && (keyWord.isEmpty())) {
				result.setListProperty(findProperties(city, 0.0, max));
				result.setKeyWord("");
				result.setMaximum(max);
				result.setMinimun(0.0);
			} else if (min != null && max == null && (keyWord.isEmpty())) {
				result.setListProperty(findProperties(city, min, 99999999999999.0));
				result.setKeyWord("");
				result.setMaximum(99999999999999.0);
				result.setMinimun(min);
			} else if (min != null && max == null && (!keyWord.isEmpty())) {
				result.setListProperty(findProperties(city, min, 99999999999999.0, keyWord));
				result.setKeyWord(keyWord);
				result.setMaximum(99999999999999.0);
				result.setMinimun(min);
			} else if (min == null && max != null && (!keyWord.isEmpty())) {
				result.setListProperty(findProperties(city, 0.0, max, keyWord));
				result.setKeyWord(keyWord);
				result.setMaximum(max);
				result.setMinimun(0.0);
			} else {
				result.setListProperty(findProperties(city));
				result.setKeyWord("");
				result.setMaximum(99999999999999.0);
				result.setMinimun(0.0);
			}
		} else {
			result = savedFinder;
		}

		return result;
	}

	// Dashboard

	public Double getAverageResultsPerFinder() {
		administratorService.findByPrincipal();

		return finderRepository.getAverageResultsPerFinder();
	}

	public Integer getMinimumResultsPerFinder() {
		administratorService.findByPrincipal();

		return finderRepository.getMinimumResultsPerFinder();
	}

	public Integer getMaximumResultsPerFinder() {
		administratorService.findByPrincipal();

		return finderRepository.getMaximumResultsPerFinder();
	}

}
