package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer>{
	
	/**
	 * Devuelve el número medio de resultados de un buscador
	 * @return
	 */
	@Query("select avg(f.listProperty.size) from Finder f")
	Double getAverageResultsPerFinder();
	
	/**
	 * Devuelve el número máximo de resultados de un buscador
	 * @return
	 */
	@Query("select min(f.listProperty.size) from Finder f")
	Integer getMinimumResultsPerFinder();
	
	/**
	 * Devuelve el número mínim de resultados de un buscador
	 * @return
	 */
	@Query("select max(f.listProperty.size) from Finder f")
	Integer getMaximumResultsPerFinder();

}
