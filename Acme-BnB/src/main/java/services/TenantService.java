
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TenantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Book;
import domain.ComentatorActor;
import domain.Comment;
import domain.Finder;
import domain.Lessor;
import domain.SocialIdentity;
import domain.Tenant;

@Service
@Transactional
public class TenantService extends ComentableService {

	//Managed repository

	@Autowired
	private TenantRepository	tenantRepository;

	//Supported services

	@Autowired
	private CommentService		commentService;


	//Constructor

	public TenantService() {
		super();
	}

	//Simple CRUD methods

	public Tenant create() {
		Tenant result = new Tenant();

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		//Configuring authority & userAccount
		authority.setAuthority("TENANT");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		Finder finder = new Finder();
		result.setFinder(finder);

		Collection<Book> books = new ArrayList<>();
		result.setBooks(books);

		Collection<SocialIdentity> socialIdentities = new ArrayList<>();
		result.setSocialIdentities(socialIdentities);

		return result;
	}

	public Collection<Tenant> findAll() {
		return tenantRepository.findAll();
	}

	public Tenant findOne(int id_tenant) {
		return tenantRepository.findOne(id_tenant);

	}

	public Tenant save(Tenant tenant) {
		Assert.notNull(tenant);
		Tenant result = tenantRepository.save(tenant);
		return result;
	}

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

	public Collection<Lessor> getLessorsPropertiesRequestedByTenantDone(Tenant tenant) {
		return tenantRepository.lessorsPropertiesRequestedByTenantDone(tenant.getId());
	}

	public Comment doComment(ComentatorActor comentatorActor, Comment comment) {
		Comment result = null;
		Assert.notNull(comentatorActor);
		Assert.notNull(comment);
		Tenant tenant = findByPrincipal();

		if (tenant.equals(comentatorActor) || getLessorsPropertiesRequestedByTenantDone(tenant).contains(comentatorActor)) {
			comment.setAuthor(tenant);
			comment.setComentableId(comentatorActor.getId());
			comment.setComentableType(comentatorActor.getClass().getSimpleName());
			result = commentService.save(comment);
		}

		return result;
	}
}
