package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{
	
	
	//Dashboard
	
	@Query("select min(p.audits.size),avg(p.audits.size),max(p.audits.size) from Property p")
	Object[] numAuditsPerProperty();
	
	@Query("select p from Property p where p.lessor.id=?1 order by p.audits.size desc")
	Collection<Property> getPropertyOrderAudits(int idLessor);
		
	@Query("select p from Property p where p.lessor.id=?1 order by p.books.size desc")
	Collection<Property> getPropertyOrderBook(int idLessor);
	
	@Query("select p from Property p join p.books b where p.lessor.id=?1 and b.status=1 order by p.books.size desc")
	Collection<Property> getPropertyOrderBookAcepted(int idLessor);
	
	@Query("select p from Property p join p.books b where p.lessor.id=?1 and b.status=2 order by p.books.size desc")
	Collection<Property> getPropertyOrderBookDenied(int idLessor);
	
	@Query("select p from Property p join p.books b where p.lessor.id=?1 and b.status=0 order by p.books.size desc")
	Collection<Property> getPropertyOrderBookPending(int idLessor);
}
