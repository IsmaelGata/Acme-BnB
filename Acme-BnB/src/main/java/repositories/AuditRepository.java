package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer>{
	
	@Query("select count(p) from Property p join p.audits a where a.auditor.id=?1 and p.id=?2")
	Integer countPropertyAuditor(int idAuditor,int idProperty);

}
