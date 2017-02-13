package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Lessor;

@Service
@Transactional
public class LessorService {
	
	@Autowired
	private LessorRepository lessorRepository;

	public LessorService() {
		super();
	}
	
	public Lessor create(){
		return null;
	}
	

	public Collection<Lessor> findAll(){
		return lessorRepository.findAll();
	}
	
	public Lessor findOne(int id_lessor){
		return lessorRepository.findOne(id_lessor);
		
	}
	
	public void save(Lessor lessor){
		lessorRepository.save(lessor);
	}
	
	public void delete(Lessor lessor){
		lessorRepository.delete(lessor);
	}
	
	//Other business methods
	
		public Lessor findByPrincipal(){
			Lessor result;
			UserAccount userAccount;
			
			userAccount = LoginService.getPrincipal();
			
			result = this.findByUserAccount(userAccount);
			Assert.notNull(result, "Any lessor with userAccountId=" + userAccount.getId() + "has be found");
			
			return result;
		}
		
		public Lessor findByUserAccount(UserAccount userAccount){
			Lessor result;
			int userAccountId;
			
			userAccountId = userAccount.getId();
			result = lessorRepository.findByUserAccountId(userAccountId);
			
			return result;
		}
		
		public Lessor findByUserName(String username){
			Assert.notNull(username);
			Lessor result;
			
			result = lessorRepository.findByUserName(username);
			
			return result;
		}

}
