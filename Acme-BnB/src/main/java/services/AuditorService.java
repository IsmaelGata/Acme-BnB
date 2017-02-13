package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Auditor;

@Service
@Transactional
public class AuditorService {
	
	@Autowired
	private AuditorRepository auditorRepository;

	public AuditorService() {
		super();
	}
	
	public Auditor create(){
		return null;
	}
	

	public Collection<Auditor> findAll(){
		return auditorRepository.findAll();
	}
	
	public Auditor findOne(int id_auditor){
		return auditorRepository.findOne(id_auditor);
		
	}
	
	public void save(Auditor auditor){
		auditorRepository.save(auditor);
	}
	
	public void delete(Auditor auditor){
		auditorRepository.delete(auditor);
	}
	
	//Other business methods
	
		public Auditor findByPrincipal(){
			Auditor result;
			UserAccount userAccount;
			
			userAccount = LoginService.getPrincipal();
			
			result = this.findByUserAccount(userAccount);
			Assert.notNull(result, "Any auditor with userAccountId=" + userAccount.getId() + "has be found");
			
			return result;
		}
		
		public Auditor findByUserAccount(UserAccount userAccount){
			Auditor result;
			int userAccountId;
			
			userAccountId = userAccount.getId();
			result = auditorRepository.findByUserAccountId(userAccountId);
			
			return result;
		}
		
		public Auditor findByUserName(String username){
			Assert.notNull(username);
			Auditor result;
			
			result = auditorRepository.findByUserName(username);
			
			return result;
		}

}
