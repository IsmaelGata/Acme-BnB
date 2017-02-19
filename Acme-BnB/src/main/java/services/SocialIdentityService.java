
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;
import form.SocialIdentityForm;

@Service
@Transactional
public class SocialIdentityService {

	//Managed repository

	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	//Supported services

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService administratorService;

	//Constructor

	public SocialIdentityService() {
		super();
	}

	//Simple CRUD methods

	public SocialIdentityForm create() {
		actorService.findByPrincipal();
		SocialIdentityForm result = new SocialIdentityForm();

		return result;
	}

	public Collection<SocialIdentity> findAll() {
		return socialIdentityRepository.findAll();
	}

	public SocialIdentity findOne(int socialIdentityId) {
		return socialIdentityRepository.findOne(socialIdentityId);

	}

	public void save(SocialIdentityForm socialIdentityForm) {
		Assert.notNull(socialIdentityForm);

		SocialIdentity result = reconstruct(socialIdentityForm);
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(result.getActor()));

		socialIdentityRepository.save(result);
	}

	public void delete(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);

		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(socialIdentity.getActor()));

		socialIdentityRepository.delete(socialIdentity);
	}

	//Other business methods

	public SocialIdentity reconstruct(SocialIdentityForm socialIdentityForm) {
		SocialIdentity result = new SocialIdentity();

		result.setNick(socialIdentityForm.getNick());
		result.setNameSocialNetwork(socialIdentityForm.getNameSocialNetwork());
		result.setURL(socialIdentityForm.getURL());

		if (socialIdentityForm.getId() != 0) {
			result.setId(socialIdentityForm.getId());
		}

		return result;
	}

	public SocialIdentityForm conversionToFormObject(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);
		SocialIdentityForm result = new SocialIdentityForm();

		result.setId(socialIdentity.getId());
		result.setNameSocialNetwork(socialIdentity.getNameSocialNetwork());
		result.setNick(socialIdentity.getNick());
		result.setURL(socialIdentity.getURL());

		return result;
	}
	
	// Dashboard
	
	public Double getAverageSocialIdentitiesPerActor() {
		administratorService.findByPrincipal();
		
		return socialIdentityRepository.getAverageSocialIdentitiesPerActor();
	}
	
	public Integer getMinimumSocialIdentitiesPerActor() {
		administratorService.findByPrincipal();
		
		return socialIdentityRepository.getMinimumSocialIdentitiesPerActor();
	}
	
	public Integer getMaximumSocialIdentitiesPerActor() {
		administratorService.findByPrincipal();
		
		return socialIdentityRepository.getMaximumSocialIdentitiesPerActor();
	}
}
