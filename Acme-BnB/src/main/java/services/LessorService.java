
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;

@Service
@Transactional
public class LessorService {

	//Managed repository

	@Autowired
	private LessorRepository	lessorRepository;


	//Supported services

	//Constructor

	public LessorService() {
		super();
	}

	//Simple CRUD methods
	public Lessor create() {
		Lessor result = new Lessor();

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		//Configuring authority & userAccount
		authority.setAuthority("LESSOR");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		Collection<Property> properties = new ArrayList<>();
		result.setProperties(properties);

		Collection<SocialIdentity> socialIdentities = new ArrayList<>();
		result.setSocialIdentities(socialIdentities);

		return result;
	}

	public Collection<Lessor> findAll() {
		return lessorRepository.findAll();
	}

	public Lessor findOne(int id_lessor) {
		return lessorRepository.findOne(id_lessor);

	}

	public Lessor save(Lessor lessor) {
		Assert.notNull(lessor);
		Lessor result;
		result = lessorRepository.save(lessor);

		return result;
	}

	public void delete(Lessor lessor) {
		Assert.notNull(lessor);

		lessorRepository.delete(lessor);
	}

	//Other business methods

	public Lessor findByPrincipal() {
		Lessor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result, "Any lessor with userAccountId=" + userAccount.getId() + "has be found");

		return result;
	}

	public Lessor findByUserAccount(UserAccount userAccount) {
		Lessor result;
		int userAccountId;

		userAccountId = userAccount.getId();
		result = lessorRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Lessor findByUserName(String username) {
		Assert.notNull(username);
		Lessor result;

		result = lessorRepository.findByUserName(username);

		return result;
	}

	public double calculateTotalAmount() {
		Lessor lessor = findByPrincipal();
		double booksAcepted = lessorRepository.acceptedBooksByLessorDone(lessor.getId());
		double result = booksAcepted - lessor.getPaid();

		return result;
	}
}
