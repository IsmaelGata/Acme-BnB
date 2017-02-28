
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
	 * Devuelve los id de los arrendadores de las propiedades que ha solicitado un inquilino dado
	 * (Se utiliza en el método doComment)
	 * 
	 * @param tenantId
	 *            , id del inquilino
	 * @return lessor's Id
	 */
	@Query("select b.property.lessor.id from Book b where b.tenant.id = ?1")
	Collection<Integer> getRequestedLessorsByTenant(int tenantId);

	// Dashboard

	/**
	 * Devuelve el/los inquilino/s con más reservas aceptadas
	 * 
	 * @return
	 */
	@Query("select t from Tenant t where (select count(b) from Book b where b.status = 1 and b.tenant.id = t.id) >= all(select count(b) from Book b where b.status = 1 group by b.tenant order by count(b) desc)")
	Collection<Tenant> getTenantsWithMoreAcceptedRequests();

	/**
	 * Devuelve el/los inquilino/s con más reservas rechazadas
	 * 
	 * @return
	 */
	@Query("select t from Tenant t where (select count(b) from Book b where b.status = 2 and b.tenant.id = t.id) >= all(select count(b) from Book b where b.status = 2 group by b.tenant order by count(b) desc)")
	Collection<Tenant> getTenantsWithMoreDeniedRequests();

	/**
	 * Devuelve el/los inquilino/s con más reservas pendientes
	 * 
	 * @return
	 */
	@Query("select t from Tenant t where (select count(b) from Book b where b.status = 0 and b.tenant.id = t.id) >= all(select count(b) from Book b where b.status = 0 group by b.tenant order by count(b) desc)")
	Collection<Tenant> getTenantsWithMorePendingRequests();

	/**
	 * Devuelve el/los inquilino/s con el mejor ratio de peticiones aceptadas
	 * 
	 * @return
	 */
	@Query("select b.tenant from Book b where (select 1.0*count(b2)/(select count(b3) from Book b3 where b3.tenant.id = b.tenant.id) from Book b2 where b2.status = 1 and b2.tenant.id = b.tenant.id) >= all(select 1.0*count(b4)/(select count(b5) from Book b5 where b5.tenant.id = b4.tenant.id) from Book b4 where b4.status = 1 group by b4.tenant) group by b.tenant")
	Collection<Tenant> getTenantsWithMaximumRatioOfApprovedRequests();

	/**
	 * Devuelve el/los inquilino/s con el peor ratio de peticiones aceptadas
	 * 
	 * @return
	 */
	@Query("select b.tenant from Book b where (select 1.0*count(b2)/(select count(b3) from Book b3 where b3.tenant.id = b.tenant.id) from Book b2 where b2.status = 1 and b2.tenant.id = b.tenant.id) <= all(select 1.0*count(b4)/(select count(b5) from Book b5 where b5.tenant.id = b4.tenant.id) from Book b4 where b4.status = 1 group by b4.tenant) group by b.tenant")
	Collection<Tenant> getTenantsWithMinimumRatioOfApprovedRequests();

}
