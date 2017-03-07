
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

	//Dashboard

	//A listing in which the attributes are sorted in descending order regarding the number of times they have been used to describe a property.
	@Query("select ea from ExtraAttribute ea order by ea.relatedValues.size desc")
	public Collection<ExtraAttribute> extraAttributesOrderByUse();
}
