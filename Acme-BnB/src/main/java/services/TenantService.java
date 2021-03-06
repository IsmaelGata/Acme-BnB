
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

import repositories.TenantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Book;
import domain.ComentatorActor;
import domain.Comment;
import domain.Finder;
import domain.SocialIdentity;
import domain.Tenant;
import form.TenantForm;

@Service
@Transactional
public class TenantService extends ComentableService {

	//Managed repository

	@Autowired
	private TenantRepository		tenantRepository;

	//Supported services

	@Autowired
	private CommentService			commentService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private FinderService			finderService;


	//Constructor

	public TenantService() {
		super();
	}

	//Simple CRUD methods

	public TenantForm create() {
		TenantForm result = new TenantForm();

		return result;
	}

	public Collection<Tenant> findAll() {
		return tenantRepository.findAll();
	}

	public Tenant findOne(int tenantId) {
		return tenantRepository.findOne(tenantId);

	}

	public Tenant save(Tenant tenant) {
		Assert.notNull(tenant);
		Finder finder = tenant.getFinder();
		tenant.setFinder(null);
		Tenant result = tenantRepository.save(tenant);
		finder.setTenant(result);
		finderService.save(finder);
		return result;
	}
	@Deprecated
	public void delete(Tenant tenant) {
		Assert.notNull(tenant);

		tenantRepository.delete(tenant);
	}

	//Other business methods

	public Tenant findByPrincipal() {
		Tenant result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result, "Any tenant with userAccountId=" + userAccount.getId() + "has be found");

		return result;
	}

	public Tenant findByUserAccount(UserAccount userAccount) {
		Tenant result;
		int userAccountId;

		userAccountId = userAccount.getId();
		result = tenantRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Tenant findByUserName(String username) {
		Assert.notNull(username);
		Tenant result;

		result = tenantRepository.findByUserName(username);

		return result;
	}

	public Collection<Integer> getRequestedLessorsByTenant(Tenant tenant) {
		return tenantRepository.getRequestedLessorsByTenant(tenant.getId());
	}

	public Comment doComment(ComentatorActor comentatorActor, Comment comment) {
		Comment result = null;
		Assert.notNull(comentatorActor);
		Assert.notNull(comment);
		Tenant tenant = findByPrincipal();

		if (tenant.getId() == comentatorActor.getId() || getRequestedLessorsByTenant(tenant).contains(comentatorActor.getId())) {
			comment.setAuthor(tenant);
			comment.setComentableId(comentatorActor.getId());
			comment.setComentableType(comentatorActor.getClass().getSimpleName());
			comment.setMoment(new Date());
			result = commentService.save(comment);
		}

		return result;
	}

	public Tenant reconstruct(TenantForm tenantForm, BindingResult binding) {
		Tenant result;

		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(tenantForm.getPassword(), null);

		result = new Tenant();

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		//Configuring authority & userAccount
		authority.setAuthority("TENANT");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		Collection<Book> books = new ArrayList<>();
		result.setBooks(books);

		Collection<SocialIdentity> socialIdentities = new ArrayList<>();
		result.setSocialIdentities(socialIdentities);

		result.getUserAccount().setUsername(tenantForm.getUsername());
		result.getUserAccount().setPassword(hash);

		result.setName(tenantForm.getName());
		result.setSurname(tenantForm.getSurname());
		result.setEmail(tenantForm.getEmail());
		result.setPhone(tenantForm.getPhone());
		result.setPicture(tenantForm.getPicture());

		//Checking passwords and conditions
		if (!tenantForm.getPassword().equals(tenantForm.getRepeatPassword())) {
			result.getUserAccount().setPassword(null);
		}

		//Creating finder

		Finder finder = new Finder();
		finder.setTenant(result);
		finder.setDestination("World");
		finder.setMaximum(99999999999999.0);
		finder.setMinimun(0.0);
		result.setFinder(finder);

		return result;
	}

	// Dashboard

	public Collection<Tenant> getTenantsWithMoreAcceptedRequests() {
		administratorService.findByPrincipal();

		return tenantRepository.getTenantsWithMoreAcceptedRequests();
	}

	public Collection<Tenant> getTenantsWithMoreDeniedRequests() {
		administratorService.findByPrincipal();

		return tenantRepository.getTenantsWithMoreDeniedRequests();
	}

	public Collection<Tenant> getTenantsWithMorePendingRequests() {
		administratorService.findByPrincipal();

		return tenantRepository.getTenantsWithMorePendingRequests();
	}

	public Collection<Tenant> getTenantsWithMaximumRatioOfApprovedRequests() {
		administratorService.findByPrincipal();

		return tenantRepository.getTenantsWithMaximumRatioOfApprovedRequests();
	}

	public Collection<Tenant> getTenantsWithMinimumRatioOfApprovedRequests() {
		administratorService.findByPrincipal();

		return tenantRepository.getTenantsWithMinimumRatioOfApprovedRequests();
	}

}
