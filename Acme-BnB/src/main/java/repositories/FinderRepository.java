
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Property;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	//Queries buscadoras de properties
	@Query("select r.property from RelatedValue r where ((r.extraAttribute.name = 'city' and r.value = ?1 and r.property.rate >= ?2 and r.property.rate <= ?3 and (r.property.name like %?4% or r.property.address like %?4% or r.property.description like %?4%)))")
	public Collection<Property> findProperty(String city, Double min, Double max, String keyWord);

	@Query("select r.property from RelatedValue r where (r.extraAttribute.name = 'city' and r.value = ?1 and r.property.rate >= ?2 and r.property.rate <= ?3)")
	public Collection<Property> findProperty(String city, Double min, Double max);

	@Query("select r.property from RelatedValue r where (r.extraAttribute.name = 'city' and r.value = ?1 and (r.property.name like %?2% or r.property.address like %?2% or r.property.description like %?2%))")
	public Collection<Property> findProperty(String city, String keyWord);

	@Query("select r.property from RelatedValue r where r.extraAttribute.name = 'city' and r.value = ?1")
	public Collection<Property> findProperty(String city);

	//****************************************************

	//Looking for saved finder
	@Query("select f from Finder f where f.destination = ?1 and f.minimun = ?2 and f.maximum = ?3 and f.keyWord like %?4%")
	public Finder getSavedFinder(String city, Double min, Double max, String keyWord);

	//****************************************************
	/**
	 * Devuelve el número medio de resultados de un buscador
	 * 
	 * @return
	 */
	@Query("select avg(f.listProperty.size) from Finder f")
	Double getAverageResultsPerFinder();

	/**
	 * Devuelve el número máximo de resultados de un buscador
	 * 
	 * @return
	 */
	@Query("select min(f.listProperty.size) from Finder f")
	Integer getMinimumResultsPerFinder();

	/**
	 * Devuelve el número mínim de resultados de un buscador
	 * 
	 * @return
	 */
	@Query("select max(f.listProperty.size) from Finder f")
	Integer getMaximumResultsPerFinder();

}
