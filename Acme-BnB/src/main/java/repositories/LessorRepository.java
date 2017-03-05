
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;
import domain.Tenant;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {

	@Query("select a from Lessor a where a.userAccount.id = ?1")
	Lessor findByUserAccountId(int id);

	@Query("select a from Lessor a where a.userAccount.username = ?1")
	Lessor findByUserName(String username);

	/**
	 * Devuelve los inquilinos que han solicitado una reserva al arrendador dado.
	 * (Se utiliza en el método doComment)
	 * 
	 * @param lessorId, el id del arrendador
	 * @return
	 */
	@Query("select distinct b.tenant.id from Book b where b.property.lessor.id = ?1")
	Collection<Integer> getRequestersTenantsByLessor(int lessorId);
	
	/**
	 * Devuelve los inquilinos que han solicitado una reserva al arrendador dado.
	 * (Se utiliza en el método doComment)
	 * 
	 * @param lessorId, el id del arrendador
	 * @return
	 */
	@Query("select distinct b.tenant from Book b where b.property.lessor.id = ?1")
	Collection<Tenant> getRequestersTenantsWithoutIdByLessor(int lessorId);
	
	// Dashboard
	
	/**
	 * Devuelve el/los arrendatario/s con más reservas aceptadas
	 * @return
	 */
	@Query("select l from Lessor l where (select count(b) from Book b where b.status = 1 and b.property.lessor.id = l.id) >= all(select count(b) from Book b where b.status = 1 group by b.property.lessor order by count(b) desc)")
	Collection<Lessor> getLessorsWithMoreAcceptedRequests();
	
	/**
	 * Devuelve el/los arrendatario/s con más reservas rechazadas
	 * @return
	 */
	@Query("select l from Lessor l where (select count(b) from Book b where b.status = 2 and b.property.lessor.id = l.id) >= all(select count(b) from Book b where b.status = 2 group by b.property.lessor order by count(b) desc)")
	Collection<Lessor> getLessorsWithMoreDeniedRequests();
	
	/**
	 * Devuelve el/los arrendatario/s con más reservas pendientes
	 * @return
	 */
	@Query("select l from Lessor l where (select count(b) from Book b where b.status = 0 and b.property.lessor.id = l.id) >= all(select count(b) from Book b where b.status = 0 group by b.property.lessor order by count(b) desc)")
	Collection<Lessor> getLessorsWithMorePendingRequests();
	
	/**
	 * Devuelve el/los arrendatario/s con el mejor ratio de peticiones aceptadas
	 * @return
	 */
	@Query("select b.property.lessor from Book b where (select 1.0*count(b2)/(select count(b3) from Book b3 where b3.property.lessor.id = b.property.lessor.id) from Book b2 where b2.status = 1 and b2.property.lessor = b.property.lessor) >= all(select 1.0*count(b4)/(select count(b5) from Book b5 where b5.property.lessor.id = b4.property.lessor.id) from Book b4 where b4.status = 1 group by b4.property.lessor) group by b.property.lessor")
	Collection<Lessor> getLessorsWithMaximumRatioOfApprovedRequests();
	
	/**
	 * Devuelve el/los arrendatario/s con el peor ratio de peticiones aceptadas
	 * @return
	 */
	@Query("select b.property.lessor from Book b where (select 1.0*count(b2)/(select count(b3) from Book b3 where b3.property.lessor.id = b.property.lessor.id) from Book b2 where b2.status = 1 and b2.property.lessor = b.property.lessor) <= all(select 1.0*count(b4)/(select count(b5) from Book b5 where b5.property.lessor.id = b4.property.lessor.id) from Book b4 where b4.status = 1 group by b4.property.lessor) group by b.property.lessor")
	Collection<Lessor> getLessorsWithMinimumRatioOfApprovedRequests();
	
}
