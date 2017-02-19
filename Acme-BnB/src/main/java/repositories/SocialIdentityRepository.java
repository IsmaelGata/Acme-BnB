package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer>{

	
	//Dashboard
	@Query("select min(a.socialIdentities.size),avg(a.socialIdentities.size),max(a.socialIdentities.size) from Actor a")
	Object[] numSocialPerProperty();
	
}
