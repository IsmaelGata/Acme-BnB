package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer>{
	
	@Query("select a from Tenant a where a.userAccount.id = ?1")
	Tenant findByUserAccountId(int id);
	
	@Query("select a from Tenant a where a.userAccount.username = ?1")
	Tenant findByUserName(String username);
}
