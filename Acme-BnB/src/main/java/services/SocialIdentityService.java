package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {
	
	@Autowired
	private SocialIdentityRepository socialIdentityRepository;

	public SocialIdentityService() {
		super();
	}
	
	public SocialIdentity create(){
		return null;
	}
	

	public Collection<SocialIdentity> findAll(){
		return socialIdentityRepository.findAll();
	}
	
	public SocialIdentity findOne(int id_socialIdentity){
		return socialIdentityRepository.findOne(id_socialIdentity);
		
	}
	
	public void save(SocialIdentity socialIdentity){
		socialIdentityRepository.save(socialIdentity);
	}
	
	public void delete(SocialIdentity socialIdentity){
		socialIdentityRepository.delete(socialIdentity);
	}
	
	//Other business methods

}
