
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.ComentatorActor;
import domain.Comment;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;
import domain.Tenant;

@Service
@Transactional
public class LessorService extends ComentableService {

	//Managed repository

	@Autowired
	private LessorRepository	lessorRepository;

	//Supported services

	@Autowired
	private CommentService		commentService;


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

	public Collection<Tenant> tenantRequestedPropertyByLessorDone(Lessor lessor) {
		Assert.notNull(lessor);
		return lessorRepository.tenantRequestedPropertyByLessorDone(lessor.getId());
	}

	/**
	 * Calcula la cantidad total que debería abonar el arrendador si acepta todas las reservas que
	 * tiene en curso (en estado PENDING) atendiendo al impuesto (FEE) que esté vigente en el momento
	 * de aceptar la reserva.
	 * 
	 * @return cantidad total a pagar
	 */
	public double calculateTotalAmount() {
		Lessor lessor = findByPrincipal();
		double result = lessorRepository.feeToPayByLessorDone(lessor.getId());

		return result;
	}

	public Comment doComment(ComentatorActor comentatorActor, Comment comment) {
		Comment result = null;
		Assert.notNull(comentatorActor);
		Assert.notNull(comment);
		Lessor lessor = findByPrincipal();

		if (lessor.equals(comentatorActor) || tenantRequestedPropertyByLessorDone(lessor).contains(comentatorActor)) {
			comment.setAuthor(lessor);
			comment.setComentableId(comentatorActor.getId());
			comment.setComentableType(comentatorActor.getClass().getSimpleName());
			comment.setMoment(new Date());
			result = commentService.save(comment);
		}

		return result;
	}
}
