
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.ComentatorActor;
import domain.Comment;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;
import domain.Tenant;
import form.LessorForm;
import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class LessorService extends ComentableService {

	//Managed repository

	@Autowired
	private LessorRepository		lessorRepository;

	//Supported services

	@Autowired
	private CommentService			commentService;

	@Autowired
	private AdministratorService	administratorService;


	//Constructor

	public LessorService() {
		super();
	}

	//Simple CRUD methods
	public LessorForm create() {
		LessorForm result = new LessorForm();

		return result;
	}

	public Collection<Lessor> findAll() {
		return lessorRepository.findAll();
	}

	public Lessor findOne(int lessorId) {
		return lessorRepository.findOne(lessorId);

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

	public Collection<Integer> getRequestersTenantsByLessor(Lessor lessor) {
		Assert.notNull(lessor);
		return lessorRepository.getRequestersTenantsByLessor(lessor.getId());
	}
	
	public Collection<Tenant> getRequestersTenantsWithoutIdByLessor(Lessor lessor) {
		Assert.notNull(lessor);
		return lessorRepository.getRequestersTenantsWithoutIdByLessor(lessor.getId());
	}

	public Comment doComment(ComentatorActor comentatorActor, Comment comment) {
		Comment result = null;
		Assert.notNull(comentatorActor);
		Assert.notNull(comment);
		Lessor lessor = findByPrincipal();

		if (lessor.getId() == comentatorActor.getId() || getRequestersTenantsByLessor(lessor).contains(comentatorActor.getId())) {
			comment.setAuthor(lessor);
			comment.setComentableId(comentatorActor.getId());
			comment.setComentableType(comentatorActor.getClass().getSimpleName());
			comment.setMoment(new Date());
			result = commentService.save(comment);
		}

		return result;
	}


	@Autowired
	Validator	validator;


	public Lessor reconstruct(LessorForm lessorForm, BindingResult binding) {
		Lessor result = new Lessor();

		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(lessorForm.getPassword(), null);

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		//Configuring authority & userAccount
		authority.setAuthority("LESSOR");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		Collection<Property> properties = new ArrayList<>();
		result.setListProperty(properties);

		Collection<SocialIdentity> socialIdentities = new ArrayList<>();
		result.setSocialIdentities(socialIdentities);

		result.getUserAccount().setUsername(lessorForm.getUsername());
		result.getUserAccount().setPassword(hash);

		result.setName(lessorForm.getName());
		result.setSurname(lessorForm.getSurname());
		result.setEmail(lessorForm.getEmail());
		result.setPhone(lessorForm.getPhone());
		result.setPicture(lessorForm.getPicture());
		result.setCreditCard(lessorForm.getCreditCard());

		//Checking passwords and conditions
		if (!lessorForm.getPassword().equals(lessorForm.getRepeatPassword())) {
			result.getUserAccount().setPassword(null);
		}

		//		validator.validate(result, binding);

		if (checkCreditCard(lessorForm.getCreditCard())) {

			//			validator.validate(lessorForm.getCreditCard(), binding);
		}

		return result;
	}

	public boolean checkCreditCard(CreditCard creditCard) {
		boolean result = true;

		if (creditCard.getBrandName().isEmpty() && creditCard.getCvv() == 0 && creditCard.getExpirationMonth() == 0 && creditCard.getExpirationYear() == 0 && creditCard.getHolderName().isEmpty() && creditCard.getNumber().isEmpty()) {
			result = false;
		}

		return result;
	}

	// Dashboard

	public Collection<Lessor> getLessorsWithMoreAcceptedRequests() {
		administratorService.findByPrincipal();

		return lessorRepository.getLessorsWithMoreAcceptedRequests();
	}

	public Collection<Lessor> getLessorsWithMoreDeniedRequests() {
		administratorService.findByPrincipal();

		return lessorRepository.getLessorsWithMoreDeniedRequests();
	}

	public Collection<Lessor> getLessorsWithMorePendingRequests() {
		administratorService.findByPrincipal();

		return lessorRepository.getLessorsWithMorePendingRequests();
	}

	public Collection<Lessor> getLessorsWithMaximumRatioOfApprovedRequests() {
		administratorService.findByPrincipal();

		return lessorRepository.getLessorsWithMaximumRatioOfApprovedRequests();
	}

	public Collection<Lessor> getLessorsWithMinimumRatioOfApprovedRequests() {
		administratorService.findByPrincipal();

		return lessorRepository.getLessorsWithMinimumRatioOfApprovedRequests();
	}

}
