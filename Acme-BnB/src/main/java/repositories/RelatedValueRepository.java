
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.RelatedValue;

@Repository
public interface RelatedValueRepository extends JpaRepository<RelatedValue, Integer> {

}
