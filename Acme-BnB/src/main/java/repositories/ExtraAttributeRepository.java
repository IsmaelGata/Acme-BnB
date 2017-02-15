
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExtraAttribute;

@Repository
public interface ExtraAttributeRepository extends JpaRepository<ExtraAttribute, Integer> {

	@Query("select ea from ExtraAttribute ea where ea.property.id = ?1 and ea.name= ?2")
	ExtraAttribute findExtraAttribute(int propertyId, String extraAttributeName);
}
