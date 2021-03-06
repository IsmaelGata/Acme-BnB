
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Lessor;
import form.ActorForm;

@Service
@Transactional
public class ActorService {

	//Managed repository

	@Autowired
	private ActorRepository	actorRepository;

	//Supported services

	@Autowired
	private LessorService	lessorService;


	public ActorService() {
		super();
	}

	public Actor create() {
		return null;
	}

	public Collection<Actor> findAll() {
		return actorRepository.findAll();
	}

	public Actor findOne(int actorId) {
		return actorRepository.findOne(actorId);

	}

	public void save(Actor actor) {
		actorRepository.save(actor);
	}

	public void delete(Actor actor) {
		actorRepository.delete(actor);
	}

	//Other business methods
	
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result, "Any actor with userAccountId=" + userAccount.getId() + "has be found");

		return result;
	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Actor result;
		int userAccountId;

		userAccountId = userAccount.getId();
		result = actorRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Actor findByUserName(String username) {
		Assert.notNull(username);
		Actor result;

		result = actorRepository.findByUserName(username);

		return result;
	}

	public Actor reconstruct(ActorForm actorForm, BindingResult binding) {
		Actor result = findByPrincipal();

		if (!binding.hasErrors()) {
			result.setEmail(actorForm.getEmail());
			result.setPhone(actorForm.getPhone());
			result.setPicture(actorForm.getPicture());
		}

		return result;
	}


	@Autowired
	private Validator	validator;


	public Lessor reconstructLessor(ActorForm actorForm, BindingResult binding) {

		Lessor result = lessorService.findByPrincipal();
		if (lessorService.checkCreditCard(actorForm.getCreditCard())) {
			validator.validate(actorForm.getCreditCard(), binding);
			result.setCreditCard(actorForm.getCreditCard());
		}
		if (!binding.hasErrors()) {
			result.setEmail(actorForm.getEmail());
			result.setPhone(actorForm.getPhone());
			result.setPicture(actorForm.getPicture());
		}

		return result;
	}
}
