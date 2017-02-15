
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface ComentableRepository {

	@Query("select c from Comment c where c.comentableId=?1")
	Collection<Comment> getComments(int comentableId);
}
