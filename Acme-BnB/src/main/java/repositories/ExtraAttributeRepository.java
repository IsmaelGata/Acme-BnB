
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExtraAttribute;

@Repository
public interface ExtraAttributeRepository extends JpaRepository<ExtraAttribute, Integer> {

	@Query("select ea from ExtraAttribute ea where ea.name not in ('Country', 'Province', 'State', 'City', 'Capacity')")
	Collection<ExtraAttribute> otherExtraAttributes();
}
