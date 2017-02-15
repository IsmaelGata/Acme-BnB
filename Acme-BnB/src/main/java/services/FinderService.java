
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Tenant;

@Service
@Transactional
public class FinderService {

	//Managed repository
	@Autowired
	private FinderRepository	finderRepository;

	//Supported services

	@Autowired
	private TenantService		tenantService;


	//Constructor

	public FinderService() {
		super();
	}

	//Simple CRUD methods

	public Collection<Finder> findAll() {
		return finderRepository.findAll();
	}

	public Finder findOne(int id_finder) {
		return finderRepository.findOne(id_finder);

	}

	public void save(Finder finder) {
		Assert.notNull(finder);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(finder.getTenant()));

		finderRepository.save(finder);
	}

	public void delete(Finder finder) {
		Assert.notNull(finder);
		Tenant tenant = tenantService.findByPrincipal();
		Assert.isTrue(tenant.equals(finder.getTenant()));

		finderRepository.delete(finder);
	}

	//Other business methods

}
