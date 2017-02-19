package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer>{

	
	// Dashboard
	
	/**
	 * Devuelve la media de identidades sociales por actor
	 * @return
	 */
	@Query("select avg(a.socialIdentities.size) from Actor a")
	Double getAverageSocialIdentitiesPerActor();
	
	@Query("select min(a.socialIdentities.size) from Actor a")
	Integer getMinimumSocialIdentitiesPerActor();
	
	@Query("select max(a.socialIdentities.size) from Actor a")
	Integer getMaximumSocialIdentitiesPerActor();
	
}
