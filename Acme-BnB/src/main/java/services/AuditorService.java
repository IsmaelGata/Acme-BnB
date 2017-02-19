
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.SocialIdentity;

@Service
@Transactional
public class AuditorService {

	//Managed repository
	@Autowired
	private AuditorRepository		auditorRepository;

	//Supported services

	@Autowired
	private AdministratorService	administratorService;


	//Constructor

	public AuditorService() {
		super();
	}

	//Simple CRUD methods
	public Auditor create() {
		Auditor result = new Auditor();

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		//Configuring authority & userAccount
		authority.setAuthority("AUDITOR");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		Collection<Audit> audits = new ArrayList<>();
		result.setAudits(audits);

		Collection<SocialIdentity> socialIdentities = new ArrayList<>();
		result.setSocialIdentities(socialIdentities);

		return result;
	}

	public Collection<Auditor> findAll() {
		return auditorRepository.findAll();
	}

	public Auditor findOne(int auditorId) {
		return auditorRepository.findOne(auditorId);

	}

	public void save(Auditor auditor) {
		Assert.notNull(auditor);
		administratorService.findByPrincipal();

		auditorRepository.save(auditor);
	}

	//Other business methods

	public Auditor findByPrincipal() {
		Auditor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result, "Any auditor with userAccountId=" + userAccount.getId() + "has be found");

		return result;
	}

	public Auditor findByUserAccount(UserAccount userAccount) {
		Auditor result;
		int userAccountId;

		userAccountId = userAccount.getId();
		result = auditorRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Auditor findByUserName(String username) {
		Assert.notNull(username);
		Auditor result;

		result = auditorRepository.findByUserName(username);

		return result;
	}

}
