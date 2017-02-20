
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ExtraAttribute;

@Repository
public interface ExtraAttributeRepository extends JpaRepository<ExtraAttribute, Integer> {

}
