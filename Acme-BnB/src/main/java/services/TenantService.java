package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TenantRepository;
import security.LoginService;
import security.UserAccount;
import domain.Tenant;

@Service
@Transactional
public class TenantService {
	
	@Autowired
	private TenantRepository tenantRepository;

	public TenantService() {
		super();
	}
	
	public Tenant create(){
		return null;
	}
	

	public Collection<Tenant> findAll(){
		return tenantRepository.findAll();
	}
	
	public Tenant findOne(int id_tenant){
		return tenantRepository.findOne(id_tenant);
		
	}
	
	public void save(Tenant tenant){
		tenantRepository.save(tenant);
	}
	
	public void delete(Tenant tenant){
		tenantRepository.delete(tenant);
	}
	
	//Other business methods
	
		public Tenant findByPrincipal(){
			Tenant result;
			UserAccount userAccount;
			
			userAccount = LoginService.getPrincipal();
			
			result = this.findByUserAccount(userAccount);
			Assert.notNull(result, "Any tenant with userAccountId=" + userAccount.getId() + "has be found");
			
			return result;
		}
		
		public Tenant findByUserAccount(UserAccount userAccount){
			Tenant result;
			int userAccountId;
			
			userAccountId = userAccount.getId();
			result = tenantRepository.findByUserAccountId(userAccountId);
			
			return result;
		}
		
		public Tenant findByUserName(String username){
			Assert.notNull(username);
			Tenant result;
			
			result = tenantRepository.findByUserName(username);
			
			return result;
		}

}
