
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
	 * (Se utiliza en el método doComment)
	 * 
	 * @param tenantId, id del inquilino
	 * @return
	 */
	@Query("select b.property.lessor.id from Book b where b.tenant.id = ?1")
	Collection<Integer> getRequestedLessorsByTenant(int tenantId);
	
	
	// Dashboard
	
	/**
	 * Devuelve el/los inquilino/s con más reservas aceptadas
	 * @return
	 */
	@Query("select t from Tenant t where (select count(b) from Book b where b.status = 1 and b.tenant.id = t.id) >= all(select count(b) from Book b where b.status = 1 group by b.tenant order by count(b) desc)")
	Collection<Lessor> getTenantsWithMoreAcceptedRequests();
	
	/**
	 * Devuelve el/los inquilino/s con más reservas rechazadas
	 * @return
	 */
	@Query("select t from Tenant t where (select count(b) from Book b where b.status = 2 and b.tenant.id = t.id) >= all(select count(b) from Book b where b.status = 2 group by b.tenant order by count(b) desc)")
	Collection<Lessor> getTenantsWithMoreDeniedRequests();
	
	/**
	 * Devuelve el/los inquilino/s con más reservas pendientes
	 * @return
	 */
	@Query("select t from Tenant t where (select count(b) from Book b where b.status = 0 and b.tenant.id = t.id) >= all(select count(b) from Book b where b.status = 0 group by b.tenant order by count(b) desc)")
	Collection<Lessor> getTenantsWithMorePendingRequests();
	
}
