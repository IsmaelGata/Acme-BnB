
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("select p.books from Lessor l join l.properties p where l.id = ?1")
	Collection<Book> findBooksByLessorAuthenticated(int id);

	//Dashboard

	/**
	 * Devuelve el número medio de reservas aceptadas por arrendador
	 * 
	 * @return
	 */
	@Query("select 1.0*count(b)/(select count(l) from Lessor l) from Book b where b.status = 1")
	Double averageAcceptedRequestPerLessor();

	/**
	 * Devuelve el número medio de reservas denegadas por arrendador
	 * 
	 * @return
	 */
	@Query("select 1.0*count(b)/(select count(l) from Lessor l) from Book b where b.status = 2")
	Double averageDeniedRequestPerLessor();

	/**
	 * Devuelve el número medio de reservas aceptadas por inquilino
	 * 
	 * @return
	 */
	@Query("select 1.0*count(b)/(select count(t) from Tenant t) from Book b where b.status = 1")
	Double averageAcceptedRequestPerTenant();

	/**
	 * Devuelve el número medio de reservas denegadas por inquilino
	 * 
	 * @return
	 */
	@Query("select 1.0*count(b)/(select count(t) from Tenant t) from Book b where b.status = 2")
	Double averageDeniedRequestPerTenant();

	/**
	 * Devuelve el número medio de reservas con auditorias versus las que no tienen auditorias
	 * 
	 * @return
	 */
	@Query("select distinct(select count(b) from Property p join p.books b where p.audits.size != 0)/ (select count(b) from Property p join p.books b where p.audits.size = 0)*1.0 from Property")
	public Double avgNumRequestWithAuditVsWithoutAudit();

}
