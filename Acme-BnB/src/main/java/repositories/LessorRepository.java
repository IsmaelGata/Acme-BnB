package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer>{

	@Query("select a from Lessor a where a.userAccount.id = ?1")
	Lessor findByUserAccountId(int id);
	
	@Query("select a from Lessor a where a.userAccount.username = ?1")
	Lessor findByUserName(String username);
}
