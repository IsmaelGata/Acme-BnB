
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {

	@Query("select a from Lessor a where a.userAccount.id = ?1")
	Lessor findByUserAccountId(int id);

	@Query("select a from Lessor a where a.userAccount.username = ?1")
	Lessor findByUserName(String username);

	/**
	 * Calcula la cantidad que debe pagar un arrendador si acepta todas las reservas que tiene
	 * disponibles, esto es, cuyo status sea PENDING
	 * 
	 * @param lessorId, el id del arrendador
	 * @return 
	 */
	@Query("select count(b)*(select f.amount from Fee f) from Book b where b.status=0 and b.property.lessor.id = ?1")
	double feeToPayByLessorDone(int lessorId);

	/**
	 * Devuelve los inquilinos que han solicitado una reserva al arrendador dado.
	 * (Se utiliza en el método doComment)
	 * 
	 * @param lessorId, el id del arrendador
	 * @return
	 */
	@Query("select distinct b.tenant.id from Book b where b.property.lessor.id = ?1")
	Collection<Integer> getRequestersTenantsByLessor(int lessorId);
	
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
	
}
