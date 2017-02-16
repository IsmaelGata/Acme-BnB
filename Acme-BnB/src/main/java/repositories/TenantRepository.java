
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;
import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("select a from Tenant a where a.userAccount.id = ?1")
	Tenant findByUserAccountId(int id);

	@Query("select a from Tenant a where a.userAccount.username = ?1")
	Tenant findByUserName(String username);

	/**
	 * Devuelve los arrendadores de las propiedades que ha solicitado un inquilino dado
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query("select b.property.lessor from Book b where b.tenant.id = ?1")
	Collection<Lessor> lessorsPropertiesRequestedByTenantDone(int tenantId);
}
