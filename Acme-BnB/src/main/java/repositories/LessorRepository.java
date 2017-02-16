
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
	 * Calcula la cantidad que debe pagar un arrendador si acepta todas las reservas que tiene
	 * disponibles, esto es, cuyo status sea PENDING
	 * 
	 * @param lessorId
	 * @return
	 */
	@Query("select count(b)*(select f.amount from Fee f) from Property p join p.books b where b.status=0 and p.lessor.id = ?1")
	double feeToPayByLessorDone(int lessorId);

	/**
	 * Devuelve los tenant que han solicitado una reserva al arrendador dado.
	 * (Se utiliza en el método doComment)
	 * 
	 * @param lessorId
	 * @return
	 */
	@Query("select distinct b.tenant from Book b where b.property.lessor.id = ?1")
	Collection<Tenant> tenantRequestedPropertyByLessorDone(int lessorId);
}
