package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Actor;
import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	


	public ActorService() {
		super();
	}
	
	public Actor create(){
		return null;
	}
	

	public Collection<Actor> findAll(){
		return actorRepository.findAll();
	}
	
	public Actor findOne(int actorId){
		return actorRepository.findOne(actorId);
		
	}
	
	public void save(Actor actor){
		actorRepository.save(actor);
	}
	
	public void delete(Actor actor){
		actorRepository.delete(actor);
	}
	
	//Other business methods
	
		public Actor findByPrincipal(){
			Actor result;
			UserAccount userAccount;
			
			userAccount = LoginService.getPrincipal();
			
			result = this.findByUserAccount(userAccount);
			Assert.notNull(result, "Any actor with userAccountId=" + userAccount.getId() + "has be found");
			
			return result;
		}
		
		public Actor findByUserAccount(UserAccount userAccount){
			Actor result;
			int userAccountId;
			
			userAccountId = userAccount.getId();
			result = actorRepository.findByUserAccountId(userAccountId);
			
			return result;
		}
		
		public Actor findByUserName(String username){
			Assert.notNull(username);
			Actor result;
			
			result = actorRepository.findByUserName(username);
			
			return result;
		}
		
		@Autowired
		Validator	validator;
		
		
		public Actor reconstruct(Actor actor, BindingResult binding) {
			Actor result;

			if(actor.getId()==0){
				result=actor;
			}else{
				result = actorRepository.findOne(actor.getId());
				
				result.setEmail(actor.getEmail());
				result.setPhone(actor.getPhone());
				result.setPicture(actor.getPicture());
				
				validator.validate(result, binding);
			}
			


			return result;
		}

}
