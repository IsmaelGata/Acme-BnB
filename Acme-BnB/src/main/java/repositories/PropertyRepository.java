
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExtraAttribute;
import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	@Query("select ea from ExtraAttribute ea where ea.name in ('Country', 'Province', 'State', 'City', 'Capacity')")
	Collection<ExtraAttribute> getDefaultExtraAttributes();

	//Dashboard

	/**
	 * Devuelve el número medio de auditorías por propiedad
	 * 
	 * @return
	 */
	@Query("select avg(p.audits.size) from Property p")
	Double getAverageAuditsPerProperty();

	/**
	 * Devuelve el número mínimo de auditorías por propiedad
	 * 
	 * @return
	 */
	@Query("select min(p.audits.size) from Property p")
	Integer getMinimumAuditsPerProperty();

	/**
	 * Devuelve el número máximo de auditorías por propiedad
	 * 
	 * @return
	 */
	@Query("select max(p.audits.size) from Property p")
	Integer getMaximumAuditsPerProperty();

	/**
	 * Devuelve un listado de propiedades, para el arrendador dado, ordenadas descendentemente según el número de auditorías que tengan
	 * 
	 * @param idLessor
	 *            , id del arrendador
	 * @return
	 */
	@Query("select p from Property p where p.lessor.id=?1 order by p.audits.size desc")
	Collection<Property> getPropertyOrderAudits(int lessorId);

	/**
	 * Devuelve un listado de propiedades, para el arrendador dado, ordenadas descendentemente según el número de solicitudes que tengan
	 * 
	 * @param idLessor
	 *            , id del arrendador
	 * @return
	 */
	@Query("select p from Property p where p.lessor.id=?1 order by p.books.size desc")
	Collection<Property> getPropertyOrderBook(int lessorId);

	/**
	 * Devuelve un listado de propiedades, para el arrendador dado, ordenadas descendentemente según el número de solicitudes aceptadas que tengan
	 * 
	 * @param idLessor
	 *            , id del arrendador
	 * @return
	 */
	@Query("select p, (select count(b) from Book b where b.property = p and b.status = 1) as c from Property p where p.lessor.id=?1 order by c desc")
	Collection<Object[]> getPropertyOrderBookAcepted(int lessorId);

	/**
	 * Devuelve un listado de propiedades, para el arrendador dado, ordenadas descendentemente según el número de solicitudes denegadas que tengan
	 * 
	 * @param idLessor
	 *            , id del arrendador
	 * @return
	 */
	@Query("select p, (select count(b) from Book b where b.property = p and b.status = 2) as c from Property p where p.lessor.id=?1 order by c desc")
	Collection<Object[]> getPropertyOrderBookDenied(int lessorId);

	/**
	 * Devuelve un listado de propiedades, para el arrendador dado, ordenadas descendentemente según el número de solicitudes pendientes que tengan
	 * 
	 * @param idLessor
	 *            , id del arrendador
	 * @return
	 */
	@Query("select p, (select count(b) from Book b where b.property = p and b.status = 0) as c from Property p where p.lessor.id=?1 order by c desc")
	Collection<Object[]> getPropertyOrderBookPending(int lessorId);
}
