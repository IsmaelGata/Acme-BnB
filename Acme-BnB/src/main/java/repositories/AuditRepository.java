
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	@Query("select count(p) from Property p join p.audits a where a.auditor.id=?1 and p.id=?2")
	Integer countPropertyAuditor(int idAuditor, int idProperty);

	@Query("select a from Audit a where a.draft = false and a.property.id = ?1")
	Collection<Audit> getDefinitiveAudits(int propertiId);

	@Query("select a from Audit a where (a.property.id = ?1 and a.auditor.id = ?2) or (a.property.id = ?1 and a.draft = false)")
	Collection<Audit> getAuditsByAuditorAndProperty(int propertyId, int auditorId);
}
