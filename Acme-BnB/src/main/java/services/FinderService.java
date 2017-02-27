
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import domain.Finder;
import domain.Tenant;
import form.FinderForm;
import repositories.FinderRepository;

@Service
@Transactional
public class FinderService {

	// Managed repository
	@Autowired
	private FinderRepository	finderRepository;

	// Supported services

	@Autowired
	private TenantService		tenantService;
	
	@Autowired
	private AdministratorService administratorService;


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

		finderRepository.save(finder);
	}

	public void delete(Finder finder) {
		Assert.notNull(finder);

		finderRepository.delete(finder);
	}

	// Other business methods
	
	public Finder reconstruct(FinderForm finderForm, BindingResult binding) {
		Finder result= new Finder();
		
		result.setDestination(finderForm.getDestination());
		result.setKeyWord(finderForm.getKeyWord());
		result.setMaximum(finderForm.getMaximum());
		result.setMinimun(finderForm.getMinimun());
		result.setMoment(new Date(System.currentTimeMillis()-10));

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
