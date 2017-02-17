
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;
import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("select a from Tenant a where a.userAccount.id = ?1")
	Tenant findByUserAccountId(int id);

	@Query("select a from Tenant a where a.userAccount.username = ?1")
	Tenant findByUserName(String username);

	/**
	 * Devuelve los arrendadores de las propiedades que ha solicitado un inquilino dado
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query("select b.property.lessor from Book b where b.tenant.id = ?1")
	Collection<Lessor> lessorsPropertiesRequestedByTenantDone(int tenantId);
	
	
	//Dashboard
	@Query("select b.tenant from Book b where b.status=1 group by b.tenant having max(b.tenant)>=(select max(b2.tenant) from Book b2 where b2.status=1")
	Collection<Tenant> tenantMoreRequestAcepted();
	
	@Query("select b.tenant from Book b where b.status=2 group by b.tenant having max(b.tenant)>=(select max(b2.tenant) from Book b2 where b2.status=2")
	Collection<Tenant> tenantMoreRequestDenied();
	
	@Query("select b.tenant from Book b where b.status=0 group by b.tenant having max(b.tenant)>=(select max(b2.tenant) from Book b2 where b2.status=0")
	Collection<Tenant> tenantMoreRequestPending();
	
	@Query("select 1.0*(count(b)/(select count(b1) from Tenant t1 join t1.books b1)) from Tenant t join t.books b where b.status=1 group by t")
	Collection<Double> avgRequestAceptedOfTenant();
	
	@Query("select 1.0*(count(b)/(select count(b1) from Tenant t1 join t1.books b1)) from Tenant t join t.books b where b.status=2 group by t")
	Collection<Double> avgRequestDeniedOfTenant();

	
}
